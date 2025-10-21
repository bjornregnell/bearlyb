package bearlyb.render

import bearlyb.video.Window
import util.Using
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.sdl.SDLRender.*
import bearlyb.video.Window.Flag.combine
import bearlyb.sdlErrorCheck

def createWindowAndRenderer(
    title: String,
    width: Int,
    height: Int,
    windowFlags: IterableOnce[Window.Flag] = Nil
  ): (window: Window, renderer: Renderer) = Using(stackPush()): stack =>
  val window   = stack.mallocPointer(1)
  val renderer = stack.mallocPointer(1)
  val flags    = windowFlags.combine
  SDL_CreateWindowAndRenderer(title, width, height, flags, window, renderer)
    .sdlErrorCheck()
  (new Window(window.get(0)), new Renderer(renderer.get(0)))
.get
