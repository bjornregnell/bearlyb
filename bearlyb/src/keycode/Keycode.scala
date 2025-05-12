package bearlyb.keycode

enum Keycode(private[bearlyb] val internal: Int):
  /** 0 */
  case Unknown                extends Keycode(0x00000000) 
  /** '\r' */
  case Return                 extends Keycode(0x0000000d) 
  /** '\x1B' */
  case Escape                 extends Keycode(0x0000001b) 
  /** '\b' */
  case Backspace              extends Keycode(0x00000008) 
  /** '\t' */
  case Tab                    extends Keycode(0x00000009) 
  /** ' ' */
  case Space                  extends Keycode(0x00000020) 
  /** '!' */
  case Exclaim                extends Keycode(0x00000021) 
  /** '"' */
  case Dblapostrophe          extends Keycode(0x00000022) 
  /** '#' */
  case Hash                   extends Keycode(0x00000023) 
  /** '$' */
  case Dollar                 extends Keycode(0x00000024) 
  /** '%' */
  case Percent                extends Keycode(0x00000025) 
  /** '&' */
  case Ampersand              extends Keycode(0x00000026) 
  /** '\'' */
  case Apostrophe             extends Keycode(0x00000027) 
  /** '(' */
  case Leftparen              extends Keycode(0x00000028) 
  /** ')' */
  case Rightparen             extends Keycode(0x00000029) 
  /** '*' */
  case Asterisk               extends Keycode(0x0000002a) 
  /** '+' */
  case Plus                   extends Keycode(0x0000002b) 
  /** ',' */
  case Comma                  extends Keycode(0x0000002c) 
  /** '-' */
  case Minus                  extends Keycode(0x0000002d) 
  /** '.' */
  case Period                 extends Keycode(0x0000002e) 
  /** '/' */
  case Slash                  extends Keycode(0x0000002f) 
  /** '0' */
  case K0                      extends Keycode(0x00000030) 
  /** '1' */
  case K1                      extends Keycode(0x00000031) 
  /** '2' */
  case K2                      extends Keycode(0x00000032) 
  /** '3' */
  case K3                      extends Keycode(0x00000033) 
  /** '4' */
  case K4                      extends Keycode(0x00000034) 
  /** '5' */
  case K5                      extends Keycode(0x00000035) 
  /** '6' */
  case K6                      extends Keycode(0x00000036) 
  /** '7' */
  case K7                      extends Keycode(0x00000037) 
  /** '8' */
  case K8                      extends Keycode(0x00000038) 
  /** '9' */
  case K9                      extends Keycode(0x00000039) 
  /** ':' */
  case Colon                  extends Keycode(0x0000003a) 
  /** ';' */
  case Semicolon              extends Keycode(0x0000003b) 
  /** '<' */
  case Less                   extends Keycode(0x0000003c) 
  /** '=' */
  case Equals                 extends Keycode(0x0000003d) 
  /** '>' */
  case Greater                extends Keycode(0x0000003e) 
  /** '?' */
  case Question               extends Keycode(0x0000003f) 
  /** '@' */
  case At                     extends Keycode(0x00000040) 
  /** '[' */
  case Leftbracket            extends Keycode(0x0000005b) 
  /** '\\' */
  case Backslash              extends Keycode(0x0000005c) 
  /** ']' */
  case Rightbracket           extends Keycode(0x0000005d) 
  /** '^' */
  case Caret                  extends Keycode(0x0000005e) 
  /** '_' */
  case Underscore             extends Keycode(0x0000005f) 
  /** '`' */
  case Grave                  extends Keycode(0x00000060) 
  /** 'a' */
  case A                      extends Keycode(0x00000061) 
  /** 'b' */
  case B                      extends Keycode(0x00000062) 
  /** 'c' */
  case C                      extends Keycode(0x00000063) 
  /** 'd' */
  case D                      extends Keycode(0x00000064) 
  /** 'e' */
  case E                      extends Keycode(0x00000065) 
  /** 'f' */
  case F                      extends Keycode(0x00000066) 
  /** 'g' */
  case G                      extends Keycode(0x00000067) 
  /** 'h' */
  case H                      extends Keycode(0x00000068) 
  /** 'i' */
  case I                      extends Keycode(0x00000069) 
  /** 'j' */
  case J                      extends Keycode(0x0000006a) 
  /** 'k' */
  case K                      extends Keycode(0x0000006b) 
  /** 'l' */
  case L                      extends Keycode(0x0000006c) 
  /** 'm' */
  case M                      extends Keycode(0x0000006d) 
  /** 'n' */
  case N                      extends Keycode(0x0000006e) 
  /** 'o' */
  case O                      extends Keycode(0x0000006f) 
  /** 'p' */
  case P                      extends Keycode(0x00000070) 
  /** 'q' */
  case Q                      extends Keycode(0x00000071) 
  /** 'r' */
  case R                      extends Keycode(0x00000072) 
  /** 's' */
  case S                      extends Keycode(0x00000073) 
  /** 't' */
  case T                      extends Keycode(0x00000074) 
  /** 'u' */
  case U                      extends Keycode(0x00000075) 
  /** 'v' */
  case V                      extends Keycode(0x00000076) 
  /** 'w' */
  case W                      extends Keycode(0x00000077) 
  /** 'x' */
  case X                      extends Keycode(0x00000078) 
  /** 'y' */
  case Y                      extends Keycode(0x00000079) 
  /** 'z' */
  case Z                      extends Keycode(0x0000007a) 
  /** '{' */
  case Leftbrace              extends Keycode(0x0000007b) 
  /** '|' */
  case Pipe                   extends Keycode(0x0000007c) 
  /** '}' */
  case Rightbrace             extends Keycode(0x0000007d) 
  /** '~' */
  case Tilde                  extends Keycode(0x0000007e) 
  /** '\x7F' */
  case Delete                 extends Keycode(0x0000007f) 
  /** '\xB1' */
  case Plusminus              extends Keycode(0x000000b1) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CAPSLOCK) */
  case Capslock               extends Keycode(0x40000039) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F1) */
  case F1                     extends Keycode(0x4000003a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F2) */
  case F2                     extends Keycode(0x4000003b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F3) */
  case F3                     extends Keycode(0x4000003c) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F4) */
  case F4                     extends Keycode(0x4000003d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F5) */
  case F5                     extends Keycode(0x4000003e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F6) */
  case F6                     extends Keycode(0x4000003f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F7) */
  case F7                     extends Keycode(0x40000040) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F8) */
  case F8                     extends Keycode(0x40000041) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F9) */
  case F9                     extends Keycode(0x40000042) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F10) */
  case F10                    extends Keycode(0x40000043) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F11) */
  case F11                    extends Keycode(0x40000044) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F12) */
  case F12                    extends Keycode(0x40000045) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PRINTSCREEN) */
  case Printscreen            extends Keycode(0x40000046) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SCROLLLOCK) */
  case Scrolllock             extends Keycode(0x40000047) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAUSE) */
  case Pause                  extends Keycode(0x40000048) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_INSERT) */
  case Insert                 extends Keycode(0x40000049) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_HOME) */
  case Home                   extends Keycode(0x4000004a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAGEUP) */
  case Pageup                 extends Keycode(0x4000004b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_END) */
  case End                    extends Keycode(0x4000004d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAGEDOWN) */
  case Pagedown               extends Keycode(0x4000004e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RIGHT) */
  case Right                  extends Keycode(0x4000004f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LEFT) */
  case Left                   extends Keycode(0x40000050) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_DOWN) */
  case Down                   extends Keycode(0x40000051) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_UP) */
  case Up                     extends Keycode(0x40000052) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_NUMLOCKCLEAR) */
  case Numlockclear           extends Keycode(0x40000053) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DIVIDE) */
  case KpDivide              extends Keycode(0x40000054) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MULTIPLY) */
  case KpMultiply            extends Keycode(0x40000055) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MINUS) */
  case KpMinus               extends Keycode(0x40000056) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PLUS) */
  case KpPlus                extends Keycode(0x40000057) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_ENTER) */
  case KpEnter               extends Keycode(0x40000058) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_1) */
  case Kp1                   extends Keycode(0x40000059) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_2) */
  case Kp2                   extends Keycode(0x4000005a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_3) */
  case Kp3                   extends Keycode(0x4000005b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_4) */
  case Kp4                   extends Keycode(0x4000005c) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_5) */
  case Kp5                   extends Keycode(0x4000005d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_6) */
  case Kp6                   extends Keycode(0x4000005e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_7) */
  case Kp7                   extends Keycode(0x4000005f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_8) */
  case Kp8                   extends Keycode(0x40000060) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_9) */
  case Kp9                   extends Keycode(0x40000061) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_0) */
  case Kp0                   extends Keycode(0x40000062) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PERIOD) */
  case KpPeriod              extends Keycode(0x40000063) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_APPLICATION) */
  case Application            extends Keycode(0x40000065) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_POWER) */
  case Power                  extends Keycode(0x40000066) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EQUALS) */
  case KpEquals              extends Keycode(0x40000067) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F13) */
  case F13                    extends Keycode(0x40000068) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F14) */
  case F14                    extends Keycode(0x40000069) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F15) */
  case F15                    extends Keycode(0x4000006a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F16) */
  case F16                    extends Keycode(0x4000006b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F17) */
  case F17                    extends Keycode(0x4000006c) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F18) */
  case F18                    extends Keycode(0x4000006d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F19) */
  case F19                    extends Keycode(0x4000006e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F20) */
  case F20                    extends Keycode(0x4000006f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F21) */
  case F21                    extends Keycode(0x40000070) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F22) */
  case F22                    extends Keycode(0x40000071) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F23) */
  case F23                    extends Keycode(0x40000072) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F24) */
  case F24                    extends Keycode(0x40000073) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_EXECUTE) */
  case Execute                extends Keycode(0x40000074) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_HELP) */
  case Help                   extends Keycode(0x40000075) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MENU) */
  case Menu                   extends Keycode(0x40000076) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SELECT) */
  case Select                 extends Keycode(0x40000077) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_STOP) */
  case Stop                   extends Keycode(0x40000078) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AGAIN) */
  case Again                  extends Keycode(0x40000079) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_UNDO) */
  case Undo                   extends Keycode(0x4000007a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CUT) */
  case Cut                    extends Keycode(0x4000007b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_COPY) */
  case Copy                   extends Keycode(0x4000007c) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PASTE) */
  case Paste                  extends Keycode(0x4000007d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_FIND) */
  case Find                   extends Keycode(0x4000007e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MUTE) */
  case Mute                   extends Keycode(0x4000007f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_VOLUMEUP) */
  case Volumeup               extends Keycode(0x40000080) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_VOLUMEDOWN) */
  case Volumedown             extends Keycode(0x40000081) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_COMMA) */
  case KpComma               extends Keycode(0x40000085) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EQUALSAS400) */
  case KpEqualsas400         extends Keycode(0x40000086) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_ALTERASE) */
  case Alterase               extends Keycode(0x40000099) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SYSREQ) */
  case Sysreq                 extends Keycode(0x4000009a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CANCEL) */
  case Cancel                 extends Keycode(0x4000009b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CLEAR) */
  case Clear                  extends Keycode(0x4000009c) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PRIOR) */
  case Prior                  extends Keycode(0x4000009d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RETURN2) */
  case Return2                extends Keycode(0x4000009e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SEPARATOR) */
  case Separator              extends Keycode(0x4000009f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_OUT) */
  case Out                    extends Keycode(0x400000a0) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_OPER) */
  case Oper                   extends Keycode(0x400000a1) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CLEARAGAIN) */
  case Clearagain             extends Keycode(0x400000a2) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CRSEL) */
  case Crsel                  extends Keycode(0x400000a3) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_EXSEL) */
  case Exsel                  extends Keycode(0x400000a4) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_00) */
  case Kp00                  extends Keycode(0x400000b0) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_000) */
  case Kp000                 extends Keycode(0x400000b1) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_THOUSANDSSEPARATOR) */
  case Thousandsseparator     extends Keycode(0x400000b2) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_DECIMALSEPARATOR) */
  case Decimalseparator       extends Keycode(0x400000b3) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CURRENCYUNIT) */
  case Currencyunit           extends Keycode(0x400000b4) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CURRENCYSUBUNIT) */
  case Currencysubunit        extends Keycode(0x400000b5) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LEFTPAREN) */
  case KpLeftparen           extends Keycode(0x400000b6) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_RIGHTPAREN) */
  case KpRightparen          extends Keycode(0x400000b7) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LEFTBRACE) */
  case KpLeftbrace           extends Keycode(0x400000b8) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_RIGHTBRACE) */
  case KpRightbrace          extends Keycode(0x400000b9) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_TAB) */
  case KpTab                 extends Keycode(0x400000ba) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_BACKSPACE) */
  case KpBackspace           extends Keycode(0x400000bb) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_A) */
  case KpA                   extends Keycode(0x400000bc) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_B) */
  case KpB                   extends Keycode(0x400000bd) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_C) */
  case KpC                   extends Keycode(0x400000be) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_D) */
  case KpD                   extends Keycode(0x400000bf) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_E) */
  case KpE                   extends Keycode(0x400000c0) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_F) */
  case KpF                   extends Keycode(0x400000c1) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_XOR) */
  case KpXor                 extends Keycode(0x400000c2) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_POWER) */
  case KpPower               extends Keycode(0x400000c3) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PERCENT) */
  case KpPercent             extends Keycode(0x400000c4) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LESS) */
  case KpLess                extends Keycode(0x400000c5) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_GREATER) */
  case KpGreater             extends Keycode(0x400000c6) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_AMPERSAND) */
  case KpAmpersand           extends Keycode(0x400000c7) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DBLAMPERSAND) */
  case KpDblampersand        extends Keycode(0x400000c8) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_VERTICALBAR) */
  case KpVerticalbar         extends Keycode(0x400000c9) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DBLVERTICALBAR) */
  case KpDblverticalbar      extends Keycode(0x400000ca) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_COLON) */
  case KpColon               extends Keycode(0x400000cb) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_HASH) */
  case KpHash                extends Keycode(0x400000cc) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_SPACE) */
  case KpSpace               extends Keycode(0x400000cd) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_AT) */
  case KpAt                  extends Keycode(0x400000ce) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EXCLAM) */
  case KpExclam              extends Keycode(0x400000cf) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMSTORE) */
  case KpMemstore            extends Keycode(0x400000d0) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMRECALL) */
  case KpMemreCall           extends Keycode(0x400000d1) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMCLEAR) */
  case KpMemclear            extends Keycode(0x400000d2) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMADD) */
  case KpMemadd              extends Keycode(0x400000d3) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMSUBTRACT) */
  case KpMemsubtract         extends Keycode(0x400000d4) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMMULTIPLY) */
  case KpMemmultiply         extends Keycode(0x400000d5) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMDIVIDE) */
  case KpMemdivide           extends Keycode(0x400000d6) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PLUSMINUS) */
  case KpPlusminus           extends Keycode(0x400000d7) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_CLEAR) */
  case KpClear               extends Keycode(0x400000d8) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_CLEARENTRY) */
  case KpClearentry          extends Keycode(0x400000d9) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_BINARY) */
  case KpBinary              extends Keycode(0x400000da) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_OCTAL) */
  case KpOctal               extends Keycode(0x400000db) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DECIMAL) */
  case KpDecimal             extends Keycode(0x400000dc) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_HEXADECIMAL) */
  case KpHexadecimal         extends Keycode(0x400000dd) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LCTRL) */
  case LCtrl                  extends Keycode(0x400000e0) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LSHIFT) */
  case LShift                 extends Keycode(0x400000e1) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LALT) */
  case LAlt                   extends Keycode(0x400000e2) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LGUI) */
  case LGui                   extends Keycode(0x400000e3) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RCTRL) */
  case RCtrl                  extends Keycode(0x400000e4) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RSHIFT) */
  case RShift                 extends Keycode(0x400000e5) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RALT) */
  case RAlt                   extends Keycode(0x400000e6) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RGUI) */
  case RGui                   extends Keycode(0x400000e7) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MODE) */
  case Mode                   extends Keycode(0x40000101) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SLEEP) */
  case Sleep                  extends Keycode(0x40000102) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_WAKE) */
  case Wake                   extends Keycode(0x40000103) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CHANNEL_INCREMENT) */
  case ChannelIncrement      extends Keycode(0x40000104) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CHANNEL_DECREMENT) */
  case ChannelDecrement      extends Keycode(0x40000105) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PLAY) */
  case MediaPlay             extends Keycode(0x40000106) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PAUSE) */
  case MediaPause            extends Keycode(0x40000107) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_RECORD) */
  case MediaRecord           extends Keycode(0x40000108) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_FAST_FORWARD) */
  case MediaFastForward     extends Keycode(0x40000109) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_REWIND) */
  case MediaRewind           extends Keycode(0x4000010a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_NEXT_TRACK) */
  case MediaNextTrack       extends Keycode(0x4000010b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PREVIOUS_TRACK) */
  case MediaPreviousTrack   extends Keycode(0x4000010c) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_STOP) */
  case MediaStop             extends Keycode(0x4000010d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_EJECT) */
  case MediaEject            extends Keycode(0x4000010e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PLAY_PAUSE) */
  case MediaPlayPause       extends Keycode(0x4000010f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_SELECT) */
  case MediaSelect           extends Keycode(0x40000110) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_NEW) */
  case AcNew                 extends Keycode(0x40000111) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_OPEN) */
  case AcOpen                extends Keycode(0x40000112) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_CLOSE) */
  case AcClose               extends Keycode(0x40000113) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_EXIT) */
  case AcExit                extends Keycode(0x40000114) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_SAVE) */
  case AcSave                extends Keycode(0x40000115) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_PRINT) */
  case AcPrint               extends Keycode(0x40000116) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_PROPERTIES) */
  case AcProperties          extends Keycode(0x40000117) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_SEARCH) */
  case AcSearch              extends Keycode(0x40000118) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_HOME) */
  case AcHome                extends Keycode(0x40000119) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_BACK) */
  case AcBack                extends Keycode(0x4000011a) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_FORWARD) */
  case AcForward             extends Keycode(0x4000011b) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_STOP) */
  case AcStop                extends Keycode(0x4000011c) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_REFRESH) */
  case AcRefresh             extends Keycode(0x4000011d) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_BOOKMARKS) */
  case AcBookmarks           extends Keycode(0x4000011e) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SOFTLEFT) */
  case Softleft               extends Keycode(0x4000011f) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SOFTRIGHT) */
  case Softright              extends Keycode(0x40000120) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CALL) */
  case Call                   extends Keycode(0x40000121) 
  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_ENDCALL) */
  case EndCall                extends Keycode(0x40000122) 
  /** Extended key Left Tab */
  case LeftTab               extends Keycode(0x20000001) 
  /** Extended key Level 5 Shift */
  case Level5Shift           extends Keycode(0x20000002) 
  /** Extended key Multi-key Compose */
  case MultiKeyCompose      extends Keycode(0x20000003) 
  /** Extended key Left Meta */
  case LMeta                  extends Keycode(0x20000004) 
  /** Extended key Right Meta */
  case RMeta                  extends Keycode(0x20000005) 
  /** Extended key Left Hyper */
  case LHyper                 extends Keycode(0x20000006) 
  /** Extended key Right Hyper */
  case RHyper                 extends Keycode(0x20000007) 
end Keycode