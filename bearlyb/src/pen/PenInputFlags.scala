package bearlyb.pen

import org.lwjgl.sdl.SDLPen.*

enum PenInputFlags(private[bearlyb] val internal: Int):
  /** pen is pressed down */
  case Down extends PenInputFlags(SDL_PEN_INPUT_DOWN)
  /** button 1 is pressed */
  case Button1 extends PenInputFlags(SDL_PEN_INPUT_BUTTON_1)
  /** button 2 is pressed */
  case Button2 extends PenInputFlags(SDL_PEN_INPUT_BUTTON_2)
  /** button 3 is pressed */
  case Button3 extends PenInputFlags(SDL_PEN_INPUT_BUTTON_3)
  /** button 4 is pressed */
  case Button4 extends PenInputFlags(SDL_PEN_INPUT_BUTTON_4)
  /** button 5 is pressed */
  case Button5 extends PenInputFlags(SDL_PEN_INPUT_BUTTON_5)
  /** eraser tip is used */
  case EraserTip extends PenInputFlags(SDL_PEN_INPUT_ERASER_TIP)

object PenInputFlags:
  extension (flags: IterableOnce[PenInputFlags])
    private[bearlyb] def combine: Int =
      flags.iterator.foldLeft(0)(_ | _.internal)

  private[bearlyb] def fromInternal(internal: Int): PenInputFlags =
    import PenInputFlags as P
    internal match
      case SDL_PEN_INPUT_DOWN => P.Down
      case SDL_PEN_INPUT_BUTTON_1 => P.Button1
      case SDL_PEN_INPUT_BUTTON_2 => P.Button2
      case SDL_PEN_INPUT_BUTTON_3 => P.Button3
      case SDL_PEN_INPUT_BUTTON_4 => P.Button4
      case SDL_PEN_INPUT_BUTTON_5 => P.Button5
      case SDL_PEN_INPUT_ERASER_TIP => P.EraserTip
end PenInputFlags