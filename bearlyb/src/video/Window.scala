package bearlyb.video

import org.lwjgl.sdl, sdl.SDLVideo.*, sdl.SDLError.*
import
  org.lwjgl.system,
  system.MemoryStack.stackPush
import scala.util.Using
import bearlyb.*
import render.Renderer
import render.Renderer

final class Window private (private[bearlyb] val pWindow: Long):
  def title: String =
    SDL_GetWindowTitle(pWindow)

  def title_=(title: String): Unit =
    SDL_SetWindowTitle(pWindow, title).sdlErrorCheck()

  def size: (Int, Int) =
    Using(stackPush()): stack =>
      val pWidth = stack.mallocInt(1)
      val pHeight = stack.mallocInt(1)
      SDL_GetWindowSize(pWindow, pWidth, pHeight)
        .sdlErrorCheck((pWidth.get(0), pHeight.get(0)))
    .get

  def size_=(width: Int, height: Int): Unit =
    SDL_SetWindowSize(pWindow, width, height).sdlErrorCheck()

  def popup(xOffset: Int, yOffset: Int, width: Int, height: Int, flags: Window.Flags*): Window =
    new Window(
      SDL_CreatePopupWindow(pWindow, xOffset, yOffset, width, height, flags.combine)
        .sdlCreationCheck()
    )

  def renderer: Renderer =
    Renderer(this)

  def destroy(): Unit =
    SDL_DestroyWindow(pWindow)

object Window:
  def apply(title: String, width: Int, height: Int, flags: Flags*): Window =
    new Window(
      SDL_CreateWindow(title, width, height, flags.combine).sdlCreationCheck()
    )

  enum Flags(private[bearlyb] val internal: Long):
    /** window is in fullscreen mode */
    case Fullscreen           extends Flags(0x0000000000000001)
    /** window usable with OpenGL context */
    case OpenGl               extends Flags(0x0000000000000002)
    /** window is occluded */
    case Occluded             extends Flags(0x0000000000000004)
    /** window is neither mapped onto the desktop nor shown in the taskbar/dock/window list; SDL_ShowWindow() is required for it to become visible */
    case Hidden               extends Flags(0x0000000000000008)
    /** no window decoration */
    case Borderless           extends Flags(0x0000000000000010)
    /** window can be resized */
    case Resizable            extends Flags(0x0000000000000020)
    /** window is minimized */
    case Minimized            extends Flags(0x0000000000000040)
    /** window is maximized */
    case Maximized            extends Flags(0x0000000000000080)
    /** window has grabbed mouse input */
    case MouseGrabbed         extends Flags(0x0000000000000100)
    /** window has input focus */
    case InputFocus           extends Flags(0x0000000000000200)
    /** window has mouse focus */
    case MouseFocus           extends Flags(0x0000000000000400)
    /** window not created by SDL */
    case External             extends Flags(0x0000000000000800)
    /** window is modal */
    case Modal                extends Flags(0x0000000000001000)
    /** window uses high pixel density back buffer if possible */
    case HighPixelDensity     extends Flags(0x0000000000002000)
    /** window has mouse captured (unrelated to MOUSE_GRABBED) */
    case MouseCapture         extends Flags(0x0000000000004000)
    /** window has relative mode enabled */
    case MouseRelativeMode    extends Flags(0x0000000000008000)
    /** window should always be above others */
    case AlwaysOnTop          extends Flags(0x0000000000010000)
    /** window should be treated as a utility window, not showing in the task bar and window list */
    case Utility              extends Flags(0x0000000000020000)
    /** window should be treated as a tooltip and does not get mouse or keyboard focus, requires a parent window */
    case Tooltip              extends Flags(0x0000000000040000)
    /** window should be treated as a popup menu, requires a parent window */
    case PopupMenu            extends Flags(0x0000000000080000)
    /** window has grabbed keyboard input */
    case KeyboardGrabbed      extends Flags(0x0000000000100000)
    /** window usable for Vulkan surface */
    case Vulkan               extends Flags(0x0000000010000000)
    /** window usable for Metal view */
    case Metal                extends Flags(0x0000000020000000)
    /** window with transparent buffer */
    case Transparent          extends Flags(0x0000000040000000)
    /** window should not be focusable */
    case NotFocusable         extends Flags(0x0000000080000000)

  object Flags:
    extension (flags: IterableOnce[Flags])
      private[bearlyb] def combine: Long =
        flags.foldLeft(0L)(_ | _.internal)
  end Flags
end Window