package bearlyb.render

import org.lwjgl.sdl.SDLRender.*
import bearlyb.*
import video.Window

class Renderer private (private[bearlyb] val pointer: Long)

object Renderer:
  def apply(window: Window): Renderer =
    new Renderer(
      SDL_CreateRenderer(window.pWindow, null.asInstanceOf[String])
        .sdlErrorCheck()
    )