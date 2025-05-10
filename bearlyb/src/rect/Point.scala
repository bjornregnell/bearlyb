package bearlyb.rect

import org.lwjgl.sdl.*
import SDLRect.*
import org.lwjgl.system.MemoryStack.*
import scala.util.Using

type Point[T] = (x: T, y: T)