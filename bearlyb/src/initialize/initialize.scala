package bearlyb.initialize

import org.lwjgl.sdl.SDLInit.*
import bearlyb.*

def init(flags: Flags*): Unit = SDL_Init(flags.combine).sdlErrorCheck()

def quit(): Unit = SDL_Quit()
