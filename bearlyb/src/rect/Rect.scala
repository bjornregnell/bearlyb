package bearlyb.rect

import org.lwjgl.sdl.*
// import SDLRect.*
// import scala.util.Using
import org.lwjgl.system.MemoryStack
// import MemoryStack.*
import scala.math.
  Numeric.Implicits.infixNumericOps,
  Ordering.Implicits.infixOrderingOps

case class Rect[T](x: T, y: T, w: T, h: T):
  // line intersections
  def intersection(x1: T, y1: T, x2: T, y2: T): (near: Option[Point[T]], far: Option[Point[T]]) = ???
  def intersection(p1: Point[T], p2: Point[T]): (near: Option[Point[T]], far: Option[Point[T]]) =
    val ((x1, y1), (x2, y2)) = (p1, p2)
    intersection(x1, y1, x2, y2)
  
  // rect intersection
  def intersection(other: Rect[T]): Option[Rect[T]] = ???

  def union(other: Rect[T]): Option[Rect[T]] = ???

  def isEmpty: Boolean = ???

  // convert to different ints
  def toFloatRect: Rect[Float] = ???
  def toIntRect: Rect[Int] = ???

object Rect:

  def empty[T: Numeric as num ] =
    new Rect(num.zero, num.zero, num.zero, num.zero)

  def enclose[T: Numeric](points: IterableOnce[Point[T]]) = ???

  private[bearlyb] def fromInternal(rect: SDL_Rect): Rect[Int] =
    new Rect(rect.x(), rect.y(), rect.w(), rect.h())

  private[bearlyb] def fromInternal(rect: SDL_FRect): Rect[Float] =
    new Rect(rect.x(), rect.y(), rect.w(), rect.h())


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
        self.equalsEpsilon(other, epsilon)

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