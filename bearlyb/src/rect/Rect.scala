package bearlyb.rect

import org.lwjgl.sdl.*
import SDLRect.*
import org.lwjgl.system.MemoryStack, MemoryStack.*
import scala.util.Using
import scala.math.
  Numeric.Implicits.infixNumericOps,
  Ordering.Implicits.infixOrderingOps

case class Rect[T](x: T, y: T, w: T, h: T)

object Rect:
  extension [T: Numeric](self: Rect[T])
    def equalsEpsilon(other: Rect[T], epsilon: T): Boolean =
      self == other ||
      (
        (self.x - other.x).abs <= epsilon &&
        (self.y - other.y).abs <= epsilon &&
        (self.w - other.w).abs <= epsilon &&
        (self.h - other.h).abs <= epsilon
      )
    
    def ~==(other: Rect[T])(using epsilon: Epsilon[T]): Boolean =
        self.equalsEpsilon(self, epsilon)

  private[bearlyb] def fromInternal(rect: SDL_Rect): Rect[Int] =
    new Rect(rect.x(), rect.y(), rect.w(), rect.h())

  private[bearlyb] def fromInternal(rect: SDL_FRect): Rect[Float] =
    new Rect(rect.x(), rect.y(), rect.w(), rect.h())

  def empty[T: Numeric as num ] =
    new Rect(num.zero, num.zero, num.zero, num.zero)

  private[bearlyb] sealed trait InternalOps[T]:
    type Internal

    extension (r: Rect[T])
      def internal(stack: MemoryStack): Internal

  given InternalOps[Int]:
    type Internal = SDL_Rect
    extension (r: Rect[Int])
      def internal(stack: MemoryStack): Internal =
        val Rect(x, y, w, h) = r
        SDL_Rect.malloc(stack).set(x, y, w, h)
  
  given InternalOps[Float]:
    type Internal = SDL_FRect
    extension (r: Rect[Float])
      def internal(stack: MemoryStack): Internal =
        val Rect(x, y, w, h) = r
        SDL_FRect.malloc(stack).set(x, y, w, h)

  opaque type Epsilon[T] = T
  object Epsilon:
    extension [T](ep: Epsilon[T])
      def value: T = ep

    def apply[T](value: T): Epsilon[T] = value
    def unapply[T](wrapped: Epsilon[T]): T = wrapped

  given Epsilon[Float] = 1.1920928955078125e-07f
  given Epsilon[Double] = 1.1920928955078125e-07d
  
end Rect