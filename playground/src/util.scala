import scala.language.experimental

extension [A](a: A)
  def **[B <: Tuple, C](f: A *: B => C): B => C =
    b => f(a *: b)