package bearlyb.rect

import org.lwjgl.sdl.*
import SDLRect.*
import org.lwjgl.system.MemoryStack.*
import scala.util.Using

type Point[T] = (T, T)

extension [T] (p: Point[T])
  def x = p(0)
  def y = p(1)

extension [CC[+x] <: scala.collection.SeqOps[x, CC, CC[x]]] (ps: CC[Point[Int]])
  def enclose(rect: Rect[Int]): CC[Point[Int]] =
    Using(stackPush()): stack => 
      val pps: SDL_Point.Buffer = SDL_Point.malloc(ps.length, stack)
      for (p, i) <- ps.zipWithIndex do
        pps.get(i).x(p.x).y(p.y)

      val r = Rect.empty[Int].internal(stack)
      SDL_GetRectEnclosingPoints(pps, rect.internal(stack),r)
      
  .get