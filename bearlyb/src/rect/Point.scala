package bearlyb.rect

import scala.util.Using
import org.lwjgl.sdl.SDL_FPoint
import org.lwjgl.system.MemoryStack, MemoryStack.stackPush
import org.lwjgl.sdl.SDLRender.SDL_RenderCoordinatesToWindow
import bearlyb.render.Renderer
import bearlyb.mallocManyFloat
import bearlyb.sdlErrorCheck

type Point[T] = (T, T)

object Point:

  extension [T: Numeric](pt: Point[T] | Null)

    private[bearlyb] def floatInternal(stack: MemoryStack): SDL_FPoint | Null =
      import Numeric.Implicits.infixNumericOps
      pt match
        case null   => null
        case (x, y) => SDL_FPoint.malloc(stack).set(x.toFloat, y.toFloat)

  extension (pt: Point[Float])

    /** Convert a point from render-coordinates into window-coordinates
      *
      * @param renderer the renderer in which the point resides
      * @return the point in window-coordinates
      */
    def toWindowCoords(renderer: Renderer): Point[Float] =
      val (x, y) = pt
      Using(stackPush()): stack =>
        val (outx, outy) = mallocManyFloat(2, stack)
        SDL_RenderCoordinatesToWindow(renderer.internal, x, y, outx, outy)
          .sdlErrorCheck()
        (outx.get(0), outy.get(0))
      .get

  end extension

end Point
