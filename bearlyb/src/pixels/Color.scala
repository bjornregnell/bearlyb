package bearlyb.pixels

import scala.math.Numeric.Implicits.infixNumericOps
import org.lwjgl.sdl.SDL_Color
import org.lwjgl.system.MemoryStack

type Color[T] = (r: T, g: T, b: T, a: T)

object Color:

  extension [T: Numeric](c: Color[T])

    def toIntColor: Color[Int] =
      val (r, g, b, a) = c
      (r.toInt, g.toInt, b.toInt, a.toInt)

    def toByteColor: Color[Byte] =
      val (r, g, b, a) = c.toIntColor
      (r.toByte, g.toByte, b.toByte, a.toByte)

    private[bearlyb] def internal(stack: MemoryStack): SDL_Color =
      val (r, g, b, a) = c.toByteColor
      SDL_Color.malloc(stack).set(r, g, b, a)

  end extension

end Color
