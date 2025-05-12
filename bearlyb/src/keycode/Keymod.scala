package bearlyb.keycode

import org.lwjgl.sdl.SDLKeycode.*

enum Keymod(private[bearlyb] val internal: Int):
  /** no modifier is applicable. */
  case None   extends Keymod(0x0000) 
  /** the left Shift key is down. */
  case LShift extends Keymod(0x0001) 
  /** the right Shift key is down. */
  case RShift extends Keymod(0x0002) 
  /** the Level 5 Shift key is down. */
  case Level5 extends Keymod(0x0004) 
  /** the left Ctrl (Control) key is down. */
  case LCtrl  extends Keymod(0x0040) 
  /** the right Ctrl (Control) key is down. */
  case RCtrl  extends Keymod(0x0080) 
  /** the left Alt key is down. */
  case LAlt   extends Keymod(0x0100) 
  /** the right Alt key is down. */
  case RAlt   extends Keymod(0x0200) 
  /** the left GUI key (often the Windows key) is down. */
  case LGui   extends Keymod(0x0400) 
  /** the right GUI key (often the Windows key) is down. */
  case RGui   extends Keymod(0x0800) 
  /** the Num Lock key (may be located on an extended keypad) is down. */
  case Num    extends Keymod(0x1000) 
  /** the Caps Lock key is down. */
  case Caps   extends Keymod(0x2000) 
  /** the !AltGr key is down. */
  case Mode   extends Keymod(0x4000) 
  /** the Scroll Lock key is down. */
  case Scroll extends Keymod(0x8000) 
  /** Any Ctrl key is down. */
  case Ctrl   extends Keymod(SDL_KMOD_CTRL)   
  /** Any Shift key is down. */
  case Shift  extends Keymod(SDL_KMOD_SHIFT) 
  /** Any Alt key is down. */
  case Alt    extends Keymod(SDL_KMOD_ALT)
  /** Any Gui (often windows key) is down. */     
  case Gui    extends Keymod(SDL_KMOD_GUI)
end Keymod