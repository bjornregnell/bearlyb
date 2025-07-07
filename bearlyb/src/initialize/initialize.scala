package bearlyb.initialize

import org.lwjgl.sdl.SDLInit.*
import org.lwjgl.sdl.SDLHints.*
import bearlyb.*

def init(flags: Flags*): Unit =
  SDL_SetHint(SDL_HINT_VIDEO_DRIVER, "wayland")
  SDL_Init(flags.combine).sdlErrorCheck()
