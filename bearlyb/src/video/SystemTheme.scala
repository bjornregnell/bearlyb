package bearlyb.video

import org.lwjgl.sdl.SDLVideo.*

enum SystemTheme:
  case Unknown, Light, Dark

object SystemTheme:

  def get: SystemTheme = SystemTheme.fromOrdinal(SDL_GetSystemTheme())
