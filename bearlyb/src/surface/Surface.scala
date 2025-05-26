package bearlyb.surface

import org.lwjgl.sdl, sdl.SDLSurface.*, sdl.SDL_Surface,
  sdl.SDLError.SDL_SetError, sdl.SDLPixels.*
import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryStack.*
import scala.util.Using
import bearlyb.*, pixels.PixelFormat
import java.nio.ByteBuffer

class Surface private (private[bearlyb] val internal: SDL_Surface, private val imageData: Option[ByteBuffer] = None):

  def clear(r: Float, g: Float, b: Float, a: Float = 1): Unit =
    SDL_ClearSurface(internal, r, g, b, a).sdlErrorCheck()

  def destroy(): Unit =
    SDL_DestroySurface(internal)
    imageData.foreach(stbi_image_free)

  def duplicate: Surface =
    new Surface(SDL_DuplicateSurface(internal).sdlCreationCheck())

  def flip(mode: FlipMode): Unit =
    SDL_FlipSurface(internal, mode.ordinal).sdlErrorCheck()

  def saveBMP(file: String): Unit =
    SDL_SaveBMP(internal, file).sdlErrorCheck()

  def mapRGB(r: Byte, g: Byte, b: Byte): Int =
    SDL_MapSurfaceRGB(internal, r, g, b)

  def mapRGBA(r: Byte, g: Byte, b: Byte, a: Byte): Int =
    SDL_MapSurfaceRGBA(internal, r, g, b, a)

end Surface

object Surface:
  def apply(width: Int, height: Int, format: PixelFormat): Surface =
    new Surface(
      SDL_CreateSurface(width, height, format.internal)
        .sdlCreationCheck()
    )

  def loadImage(filename: String, pixelFormat: PixelFormat | Null): Surface =
    lazy val errPrefix = s"Failed to load image '$filename':${System.lineSeparator()}"
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
                s"$errPrefix${ stbi_failure_reason() }"
              )
              sdlError()
        (width.get(0), height.get(0), channels.get(0), imageData)
      .get
    val internalPixelFormat = pixelFormat match
      case PixelFormat(format) => format
      case null =>
        channels match
        case 4 => SDL_PIXELFORMAT_RGBA32
        case 3 => SDL_PIXELFORMAT_RGB24
        case n =>
          SDL_SetError(
            s"$errPrefix'$n' is not a supported number of channels per pixel. Please specify a pixelformat."
          )
          sdlError()
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

end Surface
