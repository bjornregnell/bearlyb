package bearlyb.surface

import org.lwjgl.sdl.SDLSurface.*

enum FlipMode:
  case None, Horizontal, Vertical // , HorizontalAndVertical

  private[bearlyb] lazy val internal: Int = this match
    case None       => SDL_FLIP_NONE
    case Horizontal => SDL_FLIP_HORIZONTAL
    case Vertical   => SDL_FLIP_VERTICAL
