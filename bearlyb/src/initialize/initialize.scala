package bearlyb.initialize

import org.lwjgl.sdl.SDLInit.*
import bearlyb.sdlErrorCheck

def init(flags: Flags*): Unit =
  SDL_Init(flags.combine).sdlErrorCheck()