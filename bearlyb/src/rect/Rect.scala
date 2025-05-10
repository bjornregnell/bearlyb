package bearlyb.rect

import org.lwjgl.sdl.*
import SDLRect.*
import org.lwjgl.system.MemoryStack, MemoryStack.*
import scala.util.Using

case class Rect[T](x: T, y: T, w: T, h: T)

object Rect:

  private[bearlyb] def fromInternal(rect: SDL_Rect): Rect[Int] =
    Rect(rect.x(), rect.y(), rect.w(), rect.h())

  private[bearlyb] def fromInternal(rect: SDL_FRect): Rect[Float] =
    Rect(rect.x(), rect.y(), rect.w(), rect.h())

  def empty[T: Numeric as num] = 
    new Rect(num.zero, num.zero, num.zero, num.zero)

  def enclose[T: RectOps as rectOps](ps: Seq[Point[T]], clip: Rect[T] | Null = null): Rect[T] = 
    rectOps.enclose(ps, clip)

  trait RectOps[T]:
    type Internal

    def enclose(ps: Seq[Point[T]], clip: Rect[T] | Null = null): Rect[T]

    extension (rect: Rect[T])  
      private[bearlyb] def internal(stack: MemoryStack): Internal

      def lineIntersection(
        x1: T,
        y1: T,
        x2: T,
        y2: T
      ): Option[(T, T, T, T)]

      def isEmpty: Boolean

      def rectIntersection(other: Rect[T]): Option[Rect[T]]

      def union(other: Rect[T]): Option[Rect[T]] 
  end RectOps

  given RectOps[Int]:
    type Internal = SDL_Rect
    
    def enclose(ps: Seq[Point[Int]], clip: Rect[Int] | Null): Rect[Int] =
      Using(stackPush()): stack => 
        val pps: SDL_Point.Buffer = SDL_Point.malloc(ps.length, stack)
        for (p, i) <- ps.zipWithIndex do
          pps.get(i).x(p.x).y(p.y)
        
        val r = Rect.empty[Int].internal(stack)
        val c = clip match
          case null => null
          case clip@Rect(_, _, _, _) => clip.internal(stack)
        
        SDL_GetRectEnclosingPoints(pps, c ,r)
        Rect.fromInternal(r)
      .get
    end enclose

    extension (rect: Rect[Int]) 
      def internal(stack: MemoryStack): Internal = 
        SDL_Rect.malloc(stack).set(rect.x, rect.y, rect.w, rect.h)

      def lineIntersection(x1: Int, y1: Int, x2: Int, y2: Int): Option[(Int, Int, Int, Int)] = 
        Using(stackPush()): stack =>
          val px1 = stack.ints(x1)
          val py1 = stack.ints(y1)
          val px2 = stack.ints(x2)
          val py2 = stack.ints(y2)
          if SDL_GetRectAndLineIntersection(rect.internal(stack), px1, py1, px2, py2)
          then Some(px1.get(0), py1.get(0), px2.get(0), py2.get(0))
          else None
        .get

      def rectIntersection(other: Rect[Int]): Option[Rect[Int]] = 
        Using(stackPush()): stack =>
          val o = other.internal(stack)
          val result = Rect.empty[Int].internal(stack)
          if SDL_GetRectIntersection(rect.internal(stack), o, result )
          then Some(Rect.fromInternal(result))
          else None
        .get

      def union(other: Rect[Int]): Option[Rect[Int]] =
        Using(stackPush()): stack =>
          val o = other.internal(stack)
          val result = Rect.empty[Int].internal(stack)
          if SDL_GetRectUnion(rect.internal(stack), o, result )
          then Some(Rect.fromInternal(result))
          else None
        .get


      

      def isEmpty: Boolean = 
        rect.h <= 0 || rect.w <= 0 // Manual implementation here in order to be consistent with the float implementation which is manual due to a bug in the library



  given RectOps[Float]:
    type Internal = SDL_FRect
    
    def enclose(ps: Seq[Point[Float]], clip: Rect[Float] | Null): Rect[Float] =
      Using(stackPush()): stack => 
        val pps: SDL_FPoint.Buffer = SDL_FPoint.malloc(ps.length, stack)
        for (p, i) <- ps.zipWithIndex do
          pps.get(i).x(p.x).y(p.y)
        
        val r = Rect.empty[Float].internal(stack)
        val c = clip match
          case null => null
          case clip@Rect(_, _, _, _) => clip.internal(stack)
        
        SDL_GetRectEnclosingPointsFloat(pps, c ,r)
        Rect.fromInternal(r)
      .get
    end enclose

    extension (rect: Rect[Float]) 
      def internal(stack: MemoryStack): Internal = 
        SDL_FRect.malloc(stack).set(rect.x, rect.y, rect.w, rect.h)

      def lineIntersection(x1: Float, y1: Float, x2: Float, y2: Float): Option[(Float, Float, Float, Float)] = 
        Using(stackPush()): stack =>
          val py1 = stack.floats(y1)
          val px2 = stack.floats(x2)
          val py2 = stack.floats(y2)
          val px1 = stack.floats(x1)
          if SDL_GetRectAndLineIntersectionFloat(rect.internal(stack), px1, py1, px2, py2)
          then Some(px1.get(0), py1.get(0), px2.get(0), py2.get(0))
          else None
        .get

      def rectIntersection(other: Rect[Float]): Option[Rect[Float]] = 
        Using(stackPush()): stack =>
          val o = other.internal(stack)
          val result = Rect.empty[Float].internal(stack)
          if SDL_GetRectIntersectionFloat(rect.internal(stack), o, result)
          then Some(Rect.fromInternal(result))
          else None
        .get

      def union(other: Rect[Float]): Option[Rect[Float]] =
        Using(stackPush()): stack =>
          val o = other.internal(stack)
          val result = Rect.empty[Float].internal(stack)
          if SDL_GetRectUnionFloat(rect.internal(stack), o, result)
          then Some(Rect.fromInternal(result))
          else None
        .get

      def isEmpty: Boolean = 
        rect.h <= 0 || rect.w <= 0 // Manual implementation here because of a bug in SDL_RectEmptyFloat where it works on SDL_Rect instead of SDL_FRect 




