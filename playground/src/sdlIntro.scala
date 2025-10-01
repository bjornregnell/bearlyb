import org.lwjgl.sdl.*, SDLInit.*, SDLError.*, SDLVideo.*, SDLEvents.*,
  SDLRender.*, SDLPixels.*, SDLProperties.*, SDLHints.*
import org.lwjgl.system.*, MemoryUtil.*, MemoryStack.stackPush
import scala.util.Using

def createWindow(width: Int, height: Int) =
  val props = SDL_CreateProperties()
  SDL_SetStringProperty(props, "title", "Hello SDL3")
  SDL_SetNumberProperty(props, "width", 800)
  SDL_SetNumberProperty(props, "height", 600)
  SDL_SetBooleanProperty(
    props, SDL_PROP_WINDOW_CREATE_WAYLAND_SURFACE_ROLE_CUSTOM_BOOLEAN, true
  )
  val window = SDL_CreateWindowWithProperties(props)
  SDL_DestroyProperties(props)
  window

end createWindow

@main
def main(): Unit =
  SDL_SetHint(SDL_HINT_VIDEO_DRIVER, "wayland")

  if !SDL_Init(SDL_INIT_VIDEO | SDL_INIT_EVENTS) then
    Console.err.println(s"Failed to initialize SDL: ${SDL_GetError()}")
    sys.exit(-1)

  Using.resources(stackPush(), SDL_Event.create()): (stack, event) =>
    val windowPtr   = stack.mallocPointer(1)
    val rendererPtr = stack.mallocPointer(1)
    SDL_CreateWindowAndRenderer(
      "Hello sdl!", 640, 480, 0, windowPtr, rendererPtr
    )
    val window    = windowPtr.get(0)
    val renderer  = rendererPtr.get(0)
    var shouldRun = true
    while shouldRun do
      while SDL_PollEvent(event) do
        if event.`type`() == SDL_EVENT_QUIT then shouldRun = false

      SDL_SetRenderDrawColorFloat(
        renderer, 1.0, 0.0, 0.0, SDL_ALPHA_OPAQUE_FLOAT
      )
      SDL_RenderClear(renderer)
      SDL_RenderPresent(renderer)
    end while

  SDL_Quit()

end main
