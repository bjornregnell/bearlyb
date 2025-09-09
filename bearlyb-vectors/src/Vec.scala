package bearlyb.vectors

import scala.language.dynamics
import scala.compiletime.ops.int.S

/** Represents a tuple containing only a single type
  * For example `Vec[Int, 3]` = `(Int, Int, Int)`
  */
type Vec[+T, Len <: Int] <: Tuple = Len match
  case 0 => EmptyTuple
  case S[n] => T *: Vec[T, n]
object Vec:
  export VecOps.given
  export swizzling.swizzle
  export swizzling.extensions as swizzleExtensions
  
  extension [T <: Tuple](tup: T)
    inline def x = tup(0)
    inline def y = tup(1)
    inline def z = tup(2)
    inline def w = tup(3)

    inline def r = tup.x
    inline def g = tup.y
    inline def b = tup.z
    inline def a = tup.w 
  end extension

  // extension [T: Numeric](a: Vec[T, 3])
  //   def cross(b: Vec[T, 3]): Vec[T, 3] =
  //     val i = a.swizzle[(1,2,0)] * b.swizzle[(2,0,1)]
  //     val j = a.swizzle[(2,0,1)] * b.swizzle[(1,2,0)]
  //     i - j
end Vec

/** Stuff you can only do with a [[Vec]]
  */
sealed trait VecOps[Tup <: Tuple, +A] extends Dynamic:
  type Return[+T] <: Tuple
  extension (tup: Tup)

    def vmap[B](f: A => B): Return[B]
    def foreach[U](f: A => U): Unit
    def count(p: A => Boolean): Int
    def find(p: A => Boolean): Option[A]
    def foldLeft[B](z: B)(op: (B, A) => B): B
    def foldRight[B](z: B)(op: (A, B) => B): B

    def sum[A1 >: A: Numeric as num]: A1 =
      tup.foldLeft(num.zero)(num.plus)

    def product[A1 >: A: Numeric as num]: A1 =
      tup.foldLeft(num.one)(num.times)

    infix def dot[A1 >: A: Numeric as num](other: Tup): A1 =
      tup.iterator
        .zip(other.iterator)
        .map(num.times.tupled)
        .sum

    infix def mul[A1 >: A: Numeric as num](other: Tup)(using VecOps[Tuple.Zip[Tup, Tup], (A1, A1)]) =
      tup.zip(other).vmap(num.times.tupled)

    infix inline def div[A1 >: A: ([x] =>> Fractional[x] | Integral[x]) as num](other: Tup)(using VecOps[Tuple.Zip[Tup, Tup], (A1, A1)]) =
      inline num match
        case frac: Fractional[A1] => tup.zip(other).vmap(frac.div)
        case int: Integral[A1] => tup.zip(other).vmap(int.quot)

    def +[A1 >: A: Numeric as num](other: Tup)(using VecOps[Tuple.Zip[Tup, Tup], (A1, A1)]) =
      tup.zip(other).vmap(num.plus)

    def -[A1 >: A: Numeric as num](other: Tup)(using VecOps[Tuple.Zip[Tup, Tup], (A1, A1)]) =
      tup.zip(other).vmap(num.minus)

    inline def /[A1 >: A: ([x] =>> Fractional[x] | Integral[x]) as num](scalar: A1) =
      inline num match
        case frac: Fractional[A1] => tup.vmap(frac.div(_, scalar))
        case int: Integral[A1] => tup.vmap(int.quot(_, scalar))

    def *[A1 >: A: Numeric as num](scalar: A1) =
      tup.vmap(num.times(_, scalar))

    def iterator: Iterator[A] =
      tup.productIterator.asInstanceOf[Iterator[A]]
    
  end extension


end VecOps

object VecOps:

  given VecOps[EmptyTuple, Nothing] with
    type Return[+T] = EmptyTuple
    extension (tup: EmptyTuple)
      def vmap[B](f: Nothing => B): Return[B] = EmptyTuple
      def foreach[U](f: Nothing => U): Unit = ()
      def count(p: Nothing => Boolean): Int = 0
      def find(p: Nothing => Boolean): Option[Nothing] = None
      def foldLeft[B](z: B)(op: (B, Nothing) => B): B = z
      def foldRight[B](z: B)(op: (Nothing, B) => B): B = z
  end given

  given [A, Tail <: Tuple, TailVecOps <: VecOps[Tail, A]](using tailVecOps: TailVecOps): VecOps[A *: Tail, A] with
    type Return[+T] = T *: tailVecOps.Return[T]
    extension (tup: A *: Tail)
      def vmap[B](f: A => B): Return[B] = 
        f(tup.head) *: tup.tail.vmap(f)

      def foreach[U](f: A => U): Unit =
        f(tup.head)
        tup.tail.foreach(f)
        
      def count(p: A => Boolean): Int =
        val x = if p(tup.head) then 1 else 0
        x + tup.tail.count(p)
      
      def find(p: A => Boolean): Option[A] =
        val x *: xs = tup
        if p(x) then Some(x)
        else xs.find(p)
      
      def foldLeft[B](z: B)(op: (B, A) => B): B =
        val acc = op(z, tup.head)
        tup.tail.foldLeft(acc)(op)
        
      def foldRight[B](z: B)(op: (A, B) => B): B =
        val result = tup.tail.foldRight(z)(op)
        op(tup.head, result)
  end given
end VecOps