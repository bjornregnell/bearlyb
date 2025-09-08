package bearlyb.surface

import org.lwjgl.sdl, sdl.SDLSurface.*, sdl.SDL_Surface,
  sdl.SDLError.SDL_SetError, sdl.SDLPixels.*
import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryStack.*
import scala.util.Using
import bearlyb.*, pixels.{PixelFormat, Color}, rect.*
import java.nio.ByteBuffer
import scala.annotation.implicitNotFound
import bearlyb.surface.Surface.RawColor
import scala.math.Numeric.Implicits.infixNumericOps

class Surface private (private[bearlyb] val internal: SDL_Surface, private val imageData: Option[ByteBuffer] = None):
  import Surface.{PixelData, Pos}

  def pixFormat =
    SDL_GetPixelFormatDetails(internal.format)
      .sdlCreationCheck()
  
  def clear(r: Float, g: Float, b: Float, a: Float = 1): Unit =
    SDL_ClearSurface(internal, r, g, b, a).sdlErrorCheck()

  /** 'Blit' a surface onto this one. Blitting is like taking a picture
    * and putting it on top of another one.
    */
  def blit(src: Surface, at: Pos, mask: Rect[Int] | Null = null): Unit =
    Using(stackPush()): stack =>
      val srcrect = mask.internal(stack)
      val dstrect = Rect(at.x, at.y, 0, 0).internal(stack)
      SDL_BlitSurface(src.internal, srcrect, this.internal, dstrect)
        .sdlErrorCheck()
    .get

  def blitScaled(src: Surface, dstmask: Rect[Int] | Null = null, srcmask: Rect[Int] | Null = null)(using scaleMode: ScaleMode): Unit =
    Using(stackPush()): stack =>
      val srcrect = srcmask.internal(stack)
      val dstrect = dstmask.internal(stack)
      SDL_BlitSurfaceScaled(src.internal, srcrect, this.internal, dstrect, scaleMode.ordinal)
        .sdlErrorCheck()
    .get

  def blitTiled(src: Surface, dstmask: Rect[Int] | Null = null, srcmask: Rect[Int] | Null = null): Unit =
    Using(stackPush()): stack =>
      val srcrect = srcmask.internal(stack)
      val dstrect = dstmask.internal(stack)
      SDL_BlitSurfaceTiled(src.internal, srcrect, this.internal, dstrect)
        .sdlErrorCheck()
    .get

  def blitTiledWithScale[T: Numeric](src: Surface, scale: T, dstmask: Rect[Int] | Null = null, srcmask: Rect[Int] | Null = null)(using scaleMode: ScaleMode): Unit =
    Using(stackPush()): stack =>
      val srcrect = srcmask.internal(stack)
      val dstrect = dstmask.internal(stack)
      SDL_BlitSurfaceTiledWithScale(src.internal, srcrect, scale.toFloat, scaleMode.ordinal, this.internal, dstrect)
        .sdlErrorCheck()
    .get

  def scaled[T: Numeric as num](by: T)(using scaleMode: ScaleMode): Surface =
    new Surface(
      SDL_ScaleSurface(internal, (num.fromInt(width) * by).toInt, (num.fromInt(height) * by).toInt, scaleMode.ordinal)
        .sdlCreationCheck()
    )

  def scaled(width: Int, height: Int)(using scaleMode: ScaleMode): Surface =
    new Surface(
      SDL_ScaleSurface(internal, width, height, scaleMode.ordinal)
        .sdlCreationCheck()
    )

  def destroy(): Unit =
    SDL_DestroySurface(internal)
    if internal.refcount == 0 then
      imageData.foreach(stbi_image_free)

  def duplicate: Surface =
    new Surface(SDL_DuplicateSurface(internal).sdlCreationCheck())

  def flip(mode: FlipMode): Unit =
    SDL_FlipSurface(internal, mode.ordinal).sdlErrorCheck()

  def saveBMP(file: String): Unit =
    SDL_SaveBMP(internal, file).sdlErrorCheck()

  def width: Int = internal.w
  def height: Int = internal.h
  def format: PixelFormat = PixelFormat.fromInternal(internal.format)

  def apply(pos: Pos): Surface.RawColor =
    assert(pos.x < internal.w && pos.y < internal.h)
    val Bpp = pixFormat.bytes_per_pixel

    val idx = pos.y * internal.pitch + pos.x * Bpp
    val raw =
      Using(stackPush()): stack =>
        val raw = stack.calloc(4)
        for i <- 0 until Bpp do
          val b = internal.pixels.get(idx + i)
          raw.put(i, b)
        raw.asIntBuffer.get(0)
      .get

    RawColor(raw)
  end apply

  def apply[T: PixelData as pix](pos: Pos): Color[T] =
    pix.get(this)(pos)

  def update(pos: Pos, color: RawColor): Unit =
    assert(pos.x < internal.w && pos.y < internal.h)
    val bpp = pixFormat.bits_per_pixel
    val Bpp = pixFormat.bytes_per_pixel
    require(bpp == 8 || bpp == 16 || bpp == 32, s"Unknown bpp: $bpp")

    val idx = pos.y * internal.pitch + pos.x * Bpp
    Using(stackPush()): stack =>
      val raw = stack.malloc(4).putInt(color.internal)
      for i <- 0 until Bpp do
        internal.pixels.put(idx + i, raw.get(i))
    .get
  end update

  def update[T: PixelData as pix](pos: Pos, color: Color[T]): Unit =
    pix.put(this)(pos, color)
  
  def update[T: PixelData as pix](x: Int, y: Int, color: Color[T]): Unit =
    pix.put(this)((x, y), color)

  def mapRGBA[T: ([x] =>> Integral[x] | Fractional[x]) as num](color: Color[T]): Surface.RawColor =
    def toByte(x: T): Byte =
      num match
        case int: Integral[T] => int.toInt(x).toByte
        case frac: Fractional[T] =>
          frac.toInt(frac.div(x, frac.one)).toByte

    val (r, g, b, a) = color
    val result = SDL_MapSurfaceRGBA(internal, toByte(r), toByte(g), toByte(b), toByte(a))
    Surface.RawColor(result)

  /** Converts a color to the raw value, depending on the surface's format.
   *
   * @param color the color to be converted
   * @tparam T the type used to represent each channel of the color, if it is an integer type,
   *           then the value must be between 0 and 255, and if it is a floating-point type
   *           (or is fractional), then the value must be in the range 0..=1
   * @return the raw color value
   */
  def mapRGB[T: ([x] =>> Integral[x] | Fractional[x]) as num](color: NamedTuple.Init[Color[T]]): Surface.RawColor =
    def toByte(x: T): Byte =
      num match
        case int: Integral[T] => int.toInt(x).toByte
        case frac: Fractional[T] =>
          frac.toInt(frac.times(x, frac.fromInt(255))).toByte

    val (r, g, b) = color
    val result = SDL_MapSurfaceRGB(internal, toByte(r), toByte(g), toByte(b))
    Surface.RawColor(result)

  def unmapRGBA(raw: Surface.RawColor): Color[Int] =
    Using(stackPush()): stack =>
      val (r, g, b, a) = mallocMany(4, stack)
      SDL_GetRGBA(raw.internal, SDL_GetPixelFormatDetails(internal.format), SDL_GetSurfacePalette(internal), r, g, b, a)
      (r.get(0).toInt, g.get(0).toInt, b.get(0).toInt, a.get(0).toInt)
    .get

  def unmapRGB(raw: Surface.RawColor): NamedTuple.Init[Color[Int]] =
    Using(stackPush()): stack =>
      val (r, g, b) = mallocMany(3, stack)
      SDL_GetRGB(raw.internal, SDL_GetPixelFormatDetails(internal.format), SDL_GetSurfacePalette(internal), r, g, b)
      (r.get(0).toInt, g.get(0).toInt, b.get(0).toInt)
    .get

  def fillRect(rect: Rect[Int], color: Surface.RawColor): Unit =
    Using(stackPush()): stack =>
      val sdlrect = rect.internal(stack)
      SDL_FillSurfaceRect(internal, sdlrect, color.internal)
        .sdlErrorCheck()
    .get

  def fillRect(x: Int, y: Int, w: Int, h: Int, color: Surface.RawColor): Unit =
    fillRect(Rect(x, y, w, h), color)

  def fillRect[T: ([x] =>> Integral[x] | Fractional[x])](rect: Rect[Int], color: Color[T]): Unit =
    fillRect(rect, mapRGBA(color))

  def fillRect[T: ([x] =>> Integral[x] | Fractional[x])](x: Int, y: Int, w: Int, h: Int, color: Color[T]): Unit =
    fillRect(x, y, w, h, mapRGBA(color))

  /** Adds an alternative version of this surface,
    * typically used for high dpi representations
    * of cursors or icons. This call adds a reference
    * to the image so you should call image.destroy()
    * after this method returns.
    * 
    * @param image
    *   alternative version of this surface,
    *   does not need to be the same format,
    *   size, or have the same content as this
    *   surface.
    */
  def addAlternateImage(image: Surface): Unit =
    SDL_AddSurfaceAlternateImage(internal, image.internal)
      .sdlErrorCheck()

  def hasAlternateImages: Boolean =
    SDL_SurfaceHasAlternateImages(internal)

  def removeAlternateImages(): Unit =
    SDL_RemoveSurfaceAlternateImages(internal)

  def getImages(): Iterator[Surface] =
    new Iterator[Surface]:
      val buffer = SDL_GetSurfaceImages(internal)
      
      override def next(): Surface =
        if !hasNext then
          throw new NoSuchElementException("no more alternate images")
        val img = buffer.get()
        new Surface(SDL_Surface.create(img))

      override def hasNext: Boolean =
        buffer.hasRemaining
    end new
  end getImages

  def lock(): Unit =
    SDL_LockSurface(internal)
      .sdlErrorCheck()
  
  def unlock(): Unit =
    SDL_UnlockSurface(internal)

  def mustLock: Boolean =
    SDL_MUSTLOCK(internal)

  override def toString: String =
    s"Surface($width, $height, $format)"

end Surface

object Surface:
  type Pos = Point[Int]

  def apply(width: Int, height: Int, format: PixelFormat = PixelFormat.RGBA32): Surface =
    new Surface(
      SDL_CreateSurface(width, height, format.internal)
        .sdlCreationCheck()
    )

  def unapply(s: Surface): Some[(width: Int, height: Int, format: PixelFormat)] =
    Some(s.width, s.height, s.format)

  private[bearlyb] def fromInternal(internal: SDL_Surface): Surface =
    new Surface(internal)

  def loadImage(filename: String, pixelFormat: PixelFormat | Null = null): Surface =
    lazy val errPrefix = s"Failed to load image '$filename':${util.Properties.lineSeparator}"
    val (width, height, channels, imageData) =
      Using(stackPush()): stack =>
        val width = stack.mallocInt(1)
        val height = stack.mallocInt(1)
        val channels = stack.mallocInt(1)
        val imageData =
          Option(stbi_load(filename, width, height, channels, 0)) match
            case Some(imageData) => imageData
            case None =>
              SDL_SetError(
                s"$errPrefix${stbi_failure_reason()}"
              )
              sdlError()
        (width.get(0), height.get(0), channels.get(0), imageData)
      .get
    val internalPixelFormat = pixelFormat match
      case null =>
        channels match
        case 4 => SDL_PIXELFORMAT_RGBA32
        case 3 => SDL_PIXELFORMAT_RGB24
        case n =>
          SDL_SetError(
            s"$errPrefix'$n' is not a supported number of channels per pixel. Please specify a pixelformat."
          )
          sdlError()
      case PixelFormat(format) => format
    val internal = SDL_CreateSurfaceFrom(
        width,
        height,
        internalPixelFormat,
        imageData,
        width * channels
      )
    if internal == null then
      stbi_image_free(imageData)
      sdlError()
    else
      new Surface(internal, Some(imageData))

  end loadImage

  def loadBMP(filename: String): Surface =
    new Surface(SDL_LoadBMP(filename).sdlCreationCheck())

  opaque type RawColor = Int
  object RawColor:
    private[bearlyb] def apply(inner: Int): RawColor =
      inner
    
    extension (color: RawColor) private[bearlyb] def internal: Int = color
  end RawColor

  @implicitNotFound("'${T}': This type cannot represent a color. Please use a numeric type.")
  sealed trait PixelData[T]:
    def get(surf: Surface)(pos: Pos): Color[T]
    def put(surf: Surface)(pos: Pos, color: Color[T]): Unit
  
  given PixelData[Float]:
    def get(surf: Surface)(pos: Pos): Color[Float] =
      Using(stackPush()): stack =>
        val (r, g, b, a) = mallocManyFloat(4, stack)
        SDL_ReadSurfacePixelFloat(surf.internal, pos.x, pos.y, r, g, b, a)
          .sdlErrorCheck(r.get(0), g.get(0), b.get(0), a.get(0))
      .get
    def put(surf: Surface)(pos: Pos, color: Color[Float]): Unit =
      val (r, g, b, a) = color
      val (x, y) = pos
      SDL_WriteSurfacePixelFloat(surf.internal, x, y, r, g, b, a)
        .sdlErrorCheck()
  end given

  given (floatPix: PixelData[Float]) => PixelData[Double]:
    def get(surf: Surface)(pos: Pos): Color[Double] =
      val (r, g, b, a) = floatPix.get(surf)(pos)
      (r.toDouble, g.toDouble, b.toDouble, a.toDouble)

    def put(surf: Surface)(pos: Pos, color: Color[Double]): Unit =
      val (r, g, b, a) = color
      floatPix.put(surf)(pos, (r.toFloat, g.toFloat, b.toFloat, a.toFloat))
  end given

  given PixelData[Byte]:
    def get(surf: Surface)(pos: Pos): Color[Byte] =
      Using(stackPush()): stack =>
        val (r, g, b, a) = mallocMany(4, stack)
        SDL_ReadSurfacePixel(surf.internal, pos.x, pos.y, r, g, b, a)
          .sdlErrorCheck(r.get(0), g.get(0), b.get(0), a.get(0))
      .get
    def put(surf: Surface)(pos: Pos, color: Color[Byte]): Unit =
      val (r, g, b, a) = color
      val (x, y) = pos
      SDL_WriteSurfacePixelFloat(surf.internal, x, y, r, g, b, a)
        .sdlErrorCheck()
  end given

  given (bytePix: PixelData[Byte]) => PixelData[Int]:
    def get(surf: Surface)(pos: Pos): Color[Int] =
      val (r, g, b, a) = bytePix.get(surf)(pos)
      (r.toInt, g.toInt, b.toInt, a.toInt)
    def put(surf: Surface)(pos: Pos, color: Color[Int]): Unit =
      val (r, g, b, a) = color
      bytePix.put(surf)(pos, (r.toByte, g.toByte, b.toByte, a.toByte))
  end given

  given (bytePix: PixelData[Byte]) => PixelData[Long]:
    def get(surf: Surface)(pos: Pos): Color[Long] =
      val (r, g, b, a) = bytePix.get(surf)(pos)
      (r.toLong, g.toLong, b.toLong, a.toLong)
    def put(surf: Surface)(pos: Pos, color: Color[Long]): Unit =
      val (r, g, b, a) = color
      bytePix.put(surf)(pos, (r.toByte, g.toByte, b.toByte, a.toByte))
  end given

  given (bytePix: PixelData[Byte]) => PixelData[BigInt]:
    def get(surf: Surface)(pos: Pos): Color[BigInt] =
      val (r, g, b, a) = bytePix.get(surf)(pos)
      (BigInt(r), BigInt(g), BigInt(b), BigInt(a))
    def put(surf: Surface)(pos: Pos, color: Color[BigInt]): Unit =
      val (r, g, b, a) = color
      bytePix.put(surf)(pos, (r.toByte, g.toByte, b.toByte, a.toByte))
  end given
end Surface
