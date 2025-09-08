package bearlyb.pixels

import scala.util.Using
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.sdl.SDL_Palette
import org.lwjgl.sdl.SDL_Color
import org.lwjgl.sdl.SDLPixels.*
import bearlyb.pixels.Color.*

case class Palette[T: Numeric](colors: Color[T]*):
  private[bearlyb] def internal: SDL_Palette =
    Using(stackPush()): stack =>
      val palette = SDL_CreatePalette(colors.size)
      val internals = colors.map(_.internal(stack))
      val buf = SDL_Color.calloc(colors.size, stack)
      internals.foreach(c => buf.put(c))
      SDL_SetPaletteColors(palette, buf, 0)
      palette
    .get