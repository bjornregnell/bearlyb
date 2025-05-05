package bearlyb.surface

import org.lwjgl.sdl,
  sdl.SDLSurface.*,
  sdl.SDL_Surface
import bearlyb.*,
  pixels.PixelFormat

class Surface private (private[bearlyb] internal: SDL_Surface):

  def clear(r: Float, g: Float, b: Float, a : Float = 1): Unit = 
    SDL_ClearSurface(internal, r, g, b, a).sdlErrorCheck()

  def destroy(): Unit = 
    SDL_DestroySurface(internal)

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

  def loadBMP(file: String): Surface =
    new Surface(SDL_LoadBMP(file).sdlCreationCheck())
  
end Surface