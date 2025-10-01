package bearlyb.keycode

opaque type Keycode = Int

object Keycode:
  /** 0 */
  val Unknown: Keycode = 0x00000000

  /** '\r' */
  val Return: Keycode = 0x0000000d

  /** '\x1B' */
  val Escape: Keycode = 0x0000001b

  /** '\b' */
  val Backspace: Keycode = 0x00000008

  /** '\t' */
  val Tab: Keycode = 0x00000009

  /** ' ' */
  val Space: Keycode = 0x00000020

  /** '!' */
  val Exclaim: Keycode = 0x00000021

  /** '"' */
  val Dblapostrophe: Keycode = 0x00000022

  /** '#' */
  val Hash: Keycode = 0x00000023

  /** '$' */
  val Dollar: Keycode = 0x00000024

  /** '%' */
  val Percent: Keycode = 0x00000025

  /** '&' */
  val Ampersand: Keycode = 0x00000026

  /** '\'' */
  val Apostrophe: Keycode = 0x00000027

  /** '(' */
  val Leftparen: Keycode = 0x00000028

  /** ')' */
  val Rightparen: Keycode = 0x00000029

  /** '*' */
  val Asterisk: Keycode = 0x0000002a

  /** '+' */
  val Plus: Keycode = 0x0000002b

  /** ',' */
  val Comma: Keycode = 0x0000002c

  /** '-' */
  val Minus: Keycode = 0x0000002d

  /** '.' */
  val Period: Keycode = 0x0000002e

  /** '/' */
  val Slash: Keycode = 0x0000002f

  /** '0' */
  val K0: Keycode = 0x00000030

  /** '1' */
  val K1: Keycode = 0x00000031

  /** '2' */
  val K2: Keycode = 0x00000032

  /** '3' */
  val K3: Keycode = 0x00000033

  /** '4' */
  val K4: Keycode = 0x00000034

  /** '5' */
  val K5: Keycode = 0x00000035

  /** '6' */
  val K6: Keycode = 0x00000036

  /** '7' */
  val K7: Keycode = 0x00000037

  /** '8' */
  val K8: Keycode = 0x00000038

  /** '9' */
  val K9: Keycode = 0x00000039

  /** ':' */
  val Colon: Keycode = 0x0000003a

  /** ';' */
  val Semicolon: Keycode = 0x0000003b

  /** '<' */
  val Less: Keycode = 0x0000003c

  /** '=' */
  val Equals: Keycode = 0x0000003d

  /** '>' */
  val Greater: Keycode = 0x0000003e

  /** '?' */
  val Question: Keycode = 0x0000003f

  /** '@' */
  val At: Keycode = 0x00000040

  /** '[' */
  val Leftbracket: Keycode = 0x0000005b

  /** '\\' */
  val Backslash: Keycode = 0x0000005c

  /** ']' */
  val Rightbracket: Keycode = 0x0000005d

  /** '^' */
  val Caret: Keycode = 0x0000005e

  /** '_' */
  val Underscore: Keycode = 0x0000005f

  /** '`' */
  val Grave: Keycode = 0x00000060

  /** 'a' */
  val A: Keycode = 0x00000061

  /** 'b' */
  val B: Keycode = 0x00000062

  /** 'c' */
  val C: Keycode = 0x00000063

  /** 'd' */
  val D: Keycode = 0x00000064

  /** 'e' */
  val E: Keycode = 0x00000065

  /** 'f' */
  val F: Keycode = 0x00000066

  /** 'g' */
  val G: Keycode = 0x00000067

  /** 'h' */
  val H: Keycode = 0x00000068

  /** 'i' */
  val I: Keycode = 0x00000069

  /** 'j' */
  val J: Keycode = 0x0000006a

  /** 'k' */
  val K: Keycode = 0x0000006b

  /** 'l' */
  val L: Keycode = 0x0000006c

  /** 'm' */
  val M: Keycode = 0x0000006d

  /** 'n' */
  val N: Keycode = 0x0000006e

  /** 'o' */
  val O: Keycode = 0x0000006f

  /** 'p' */
  val P: Keycode = 0x00000070

  /** 'q' */
  val Q: Keycode = 0x00000071

  /** 'r' */
  val R: Keycode = 0x00000072

  /** 's' */
  val S: Keycode = 0x00000073

  /** 't' */
  val T: Keycode = 0x00000074

  /** 'u' */
  val U: Keycode = 0x00000075

  /** 'v' */
  val V: Keycode = 0x00000076

  /** 'w' */
  val W: Keycode = 0x00000077

  /** 'x' */
  val X: Keycode = 0x00000078

  /** 'y' */
  val Y: Keycode = 0x00000079

  /** 'z' */
  val Z: Keycode = 0x0000007a

  /** '{' */
  val Leftbrace: Keycode = 0x0000007b

  /** '|' */
  val Pipe: Keycode = 0x0000007c

  /** '}' */
  val Rightbrace: Keycode = 0x0000007d

  /** '~' */
  val Tilde: Keycode = 0x0000007e

  /** '\x7F' */
  val Delete: Keycode = 0x0000007f

  /** '\xB1' */
  val Plusminus: Keycode = 0x000000b1

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CAPSLOCK) */
  val Capslock: Keycode = 0x40000039

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F1) */
  val F1: Keycode = 0x4000003a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F2) */
  val F2: Keycode = 0x4000003b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F3) */
  val F3: Keycode = 0x4000003c

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F4) */
  val F4: Keycode = 0x4000003d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F5) */
  val F5: Keycode = 0x4000003e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F6) */
  val F6: Keycode = 0x4000003f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F7) */
  val F7: Keycode = 0x40000040

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F8) */
  val F8: Keycode = 0x40000041

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F9) */
  val F9: Keycode = 0x40000042

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F10) */
  val F10: Keycode = 0x40000043

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F11) */
  val F11: Keycode = 0x40000044

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F12) */
  val F12: Keycode = 0x40000045

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PRINTSCREEN) */
  val Printscreen: Keycode = 0x40000046

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SCROLLLOCK) */
  val Scrolllock: Keycode = 0x40000047

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAUSE) */
  val Pause: Keycode = 0x40000048

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_INSERT) */
  val Insert: Keycode = 0x40000049

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_HOME) */
  val Home: Keycode = 0x4000004a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAGEUP) */
  val Pageup: Keycode = 0x4000004b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_END) */
  val End: Keycode = 0x4000004d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAGEDOWN) */
  val Pagedown: Keycode = 0x4000004e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RIGHT) */
  val Right: Keycode = 0x4000004f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LEFT) */
  val Left: Keycode = 0x40000050

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_DOWN) */
  val Down: Keycode = 0x40000051

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_UP) */
  val Up: Keycode = 0x40000052

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_NUMLOCKCLEAR) */
  val Numlockclear: Keycode = 0x40000053

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DIVIDE) */
  val KpDivide: Keycode = 0x40000054

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MULTIPLY) */
  val KpMultiply: Keycode = 0x40000055

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MINUS) */
  val KpMinus: Keycode = 0x40000056

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PLUS) */
  val KpPlus: Keycode = 0x40000057

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_ENTER) */
  val KpEnter: Keycode = 0x40000058

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_1) */
  val Kp1: Keycode = 0x40000059

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_2) */
  val Kp2: Keycode = 0x4000005a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_3) */
  val Kp3: Keycode = 0x4000005b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_4) */
  val Kp4: Keycode = 0x4000005c

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_5) */
  val Kp5: Keycode = 0x4000005d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_6) */
  val Kp6: Keycode = 0x4000005e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_7) */
  val Kp7: Keycode = 0x4000005f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_8) */
  val Kp8: Keycode = 0x40000060

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_9) */
  val Kp9: Keycode = 0x40000061

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_0) */
  val Kp0: Keycode = 0x40000062

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PERIOD) */
  val KpPeriod: Keycode = 0x40000063

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_APPLICATION) */
  val Application: Keycode = 0x40000065

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_POWER) */
  val Power: Keycode = 0x40000066

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EQUALS) */
  val KpEquals: Keycode = 0x40000067

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F13) */
  val F13: Keycode = 0x40000068

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F14) */
  val F14: Keycode = 0x40000069

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F15) */
  val F15: Keycode = 0x4000006a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F16) */
  val F16: Keycode = 0x4000006b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F17) */
  val F17: Keycode = 0x4000006c

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F18) */
  val F18: Keycode = 0x4000006d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F19) */
  val F19: Keycode = 0x4000006e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F20) */
  val F20: Keycode = 0x4000006f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F21) */
  val F21: Keycode = 0x40000070

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F22) */
  val F22: Keycode = 0x40000071

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F23) */
  val F23: Keycode = 0x40000072

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F24) */
  val F24: Keycode = 0x40000073

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_EXECUTE) */
  val Execute: Keycode = 0x40000074

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_HELP) */
  val Help: Keycode = 0x40000075

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MENU) */
  val Menu: Keycode = 0x40000076

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SELECT) */
  val Select: Keycode = 0x40000077

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_STOP) */
  val Stop: Keycode = 0x40000078

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AGAIN) */
  val Again: Keycode = 0x40000079

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_UNDO) */
  val Undo: Keycode = 0x4000007a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CUT) */
  val Cut: Keycode = 0x4000007b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_COPY) */
  val Copy: Keycode = 0x4000007c

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PASTE) */
  val Paste: Keycode = 0x4000007d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_FIND) */
  val Find: Keycode = 0x4000007e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MUTE) */
  val Mute: Keycode = 0x4000007f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_VOLUMEUP) */
  val Volumeup: Keycode = 0x40000080

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_VOLUMEDOWN) */
  val Volumedown: Keycode = 0x40000081

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_COMMA) */
  val KpComma: Keycode = 0x40000085

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EQUALSAS400) */
  val KpEqualsas400: Keycode = 0x40000086

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_ALTERASE) */
  val Alterase: Keycode = 0x40000099

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SYSREQ) */
  val Sysreq: Keycode = 0x4000009a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CANCEL) */
  val Cancel: Keycode = 0x4000009b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CLEAR) */
  val Clear: Keycode = 0x4000009c

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PRIOR) */
  val Prior: Keycode = 0x4000009d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RETURN2) */
  val Return2: Keycode = 0x4000009e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SEPARATOR) */
  val Separator: Keycode = 0x4000009f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_OUT) */
  val Out: Keycode = 0x400000a0

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_OPER) */
  val Oper: Keycode = 0x400000a1

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CLEARAGAIN) */
  val Clearagain: Keycode = 0x400000a2

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CRSEL) */
  val Crsel: Keycode = 0x400000a3

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_EXSEL) */
  val Exsel: Keycode = 0x400000a4

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_00) */
  val Kp00: Keycode = 0x400000b0

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_000) */
  val Kp000: Keycode = 0x400000b1

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_THOUSANDSSEPARATOR) */
  val Thousandsseparator: Keycode = 0x400000b2

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_DECIMALSEPARATOR) */
  val Decimalseparator: Keycode = 0x400000b3

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CURRENCYUNIT) */
  val Currencyunit: Keycode = 0x400000b4

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CURRENCYSUBUNIT) */
  val Currencysubunit: Keycode = 0x400000b5

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LEFTPAREN) */
  val KpLeftparen: Keycode = 0x400000b6

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_RIGHTPAREN) */
  val KpRightparen: Keycode = 0x400000b7

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LEFTBRACE) */
  val KpLeftbrace: Keycode = 0x400000b8

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_RIGHTBRACE) */
  val KpRightbrace: Keycode = 0x400000b9

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_TAB) */
  val KpTab: Keycode = 0x400000ba

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_BACKSPACE) */
  val KpBackspace: Keycode = 0x400000bb

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_A) */
  val KpA: Keycode = 0x400000bc

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_B) */
  val KpB: Keycode = 0x400000bd

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_C) */
  val KpC: Keycode = 0x400000be

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_D) */
  val KpD: Keycode = 0x400000bf

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_E) */
  val KpE: Keycode = 0x400000c0

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_F) */
  val KpF: Keycode = 0x400000c1

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_XOR) */
  val KpXor: Keycode = 0x400000c2

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_POWER) */
  val KpPower: Keycode = 0x400000c3

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PERCENT) */
  val KpPercent: Keycode = 0x400000c4

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LESS) */
  val KpLess: Keycode = 0x400000c5

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_GREATER) */
  val KpGreater: Keycode = 0x400000c6

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_AMPERSAND) */
  val KpAmpersand: Keycode = 0x400000c7

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DBLAMPERSAND) */
  val KpDblampersand: Keycode = 0x400000c8

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_VERTICALBAR) */
  val KpVerticalbar: Keycode = 0x400000c9

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DBLVERTICALBAR) */
  val KpDblverticalbar: Keycode = 0x400000ca

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_COLON) */
  val KpColon: Keycode = 0x400000cb

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_HASH) */
  val KpHash: Keycode = 0x400000cc

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_SPACE) */
  val KpSpace: Keycode = 0x400000cd

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_AT) */
  val KpAt: Keycode = 0x400000ce

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EXCLAM) */
  val KpExclam: Keycode = 0x400000cf

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMSTORE) */
  val KpMemstore: Keycode = 0x400000d0

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMRECALL) */
  val KpMemreCall: Keycode = 0x400000d1

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMCLEAR) */
  val KpMemclear: Keycode = 0x400000d2

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMADD) */
  val KpMemadd: Keycode = 0x400000d3

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMSUBTRACT) */
  val KpMemsubtract: Keycode = 0x400000d4

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMMULTIPLY) */
  val KpMemmultiply: Keycode = 0x400000d5

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMDIVIDE) */
  val KpMemdivide: Keycode = 0x400000d6

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PLUSMINUS) */
  val KpPlusminus: Keycode = 0x400000d7

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_CLEAR) */
  val KpClear: Keycode = 0x400000d8

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_CLEARENTRY) */
  val KpClearentry: Keycode = 0x400000d9

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_BINARY) */
  val KpBinary: Keycode = 0x400000da

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_OCTAL) */
  val KpOctal: Keycode = 0x400000db

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DECIMAL) */
  val KpDecimal: Keycode = 0x400000dc

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_HEXADECIMAL) */
  val KpHexadecimal: Keycode = 0x400000dd

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LCTRL) */
  val LCtrl: Keycode = 0x400000e0

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LSHIFT) */
  val LShift: Keycode = 0x400000e1

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LALT) */
  val LAlt: Keycode = 0x400000e2

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LGUI) */
  val LGui: Keycode = 0x400000e3

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RCTRL) */
  val RCtrl: Keycode = 0x400000e4

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RSHIFT) */
  val RShift: Keycode = 0x400000e5

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RALT) */
  val RAlt: Keycode = 0x400000e6

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RGUI) */
  val RGui: Keycode = 0x400000e7

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MODE) */
  val Mode: Keycode = 0x40000101

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SLEEP) */
  val Sleep: Keycode = 0x40000102

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_WAKE) */
  val Wake: Keycode = 0x40000103

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CHANNEL_INCREMENT) */
  val ChannelIncrement: Keycode = 0x40000104

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CHANNEL_DECREMENT) */
  val ChannelDecrement: Keycode = 0x40000105

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PLAY) */
  val MediaPlay: Keycode = 0x40000106

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PAUSE) */
  val MediaPause: Keycode = 0x40000107

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_RECORD) */
  val MediaRecord: Keycode = 0x40000108

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_FAST_FORWARD) */
  val MediaFastForward: Keycode = 0x40000109

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_REWIND) */
  val MediaRewind: Keycode = 0x4000010a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_NEXT_TRACK) */
  val MediaNextTrack: Keycode = 0x4000010b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PREVIOUS_TRACK) */
  val MediaPreviousTrack: Keycode = 0x4000010c

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_STOP) */
  val MediaStop: Keycode = 0x4000010d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_EJECT) */
  val MediaEject: Keycode = 0x4000010e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PLAY_PAUSE) */
  val MediaPlayPause: Keycode = 0x4000010f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_SELECT) */
  val MediaSelect: Keycode = 0x40000110

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_NEW) */
  val AcNew: Keycode = 0x40000111

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_OPEN) */
  val AcOpen: Keycode = 0x40000112

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_CLOSE) */
  val AcClose: Keycode = 0x40000113

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_EXIT) */
  val AcExit: Keycode = 0x40000114

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_SAVE) */
  val AcSave: Keycode = 0x40000115

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_PRINT) */
  val AcPrint: Keycode = 0x40000116

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_PROPERTIES) */
  val AcProperties: Keycode = 0x40000117

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_SEARCH) */
  val AcSearch: Keycode = 0x40000118

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_HOME) */
  val AcHome: Keycode = 0x40000119

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_BACK) */
  val AcBack: Keycode = 0x4000011a

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_FORWARD) */
  val AcForward: Keycode = 0x4000011b

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_STOP) */
  val AcStop: Keycode = 0x4000011c

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_REFRESH) */
  val AcRefresh: Keycode = 0x4000011d

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_BOOKMARKS) */
  val AcBookmarks: Keycode = 0x4000011e

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SOFTLEFT) */
  val Softleft: Keycode = 0x4000011f

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SOFTRIGHT) */
  val Softright: Keycode = 0x40000120

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CALL) */
  val Call: Keycode = 0x40000121

  /** SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_ENDCALL) */
  val EndCall: Keycode = 0x40000122

  /** Extended key Left Tab */
  val LeftTab: Keycode = 0x20000001

  /** Extended key Level 5 Shift */
  val Level5Shift: Keycode = 0x20000002

  /** Extended key Multi-key Compose */
  val MultiKeyCompose: Keycode = 0x20000003

  /** Extended key Left Meta */
  val LMeta: Keycode = 0x20000004

  /** Extended key Right Meta */
  val RMeta: Keycode = 0x20000005

  /** Extended key Left Hyper */
  val LHyper: Keycode = 0x20000006

  /** Extended key Right Hyper */
  val RHyper: Keycode = 0x20000007

  extension (key: Keycode) private[bearlyb] def internal: Int = key

  private[bearlyb] def fromInternal(internal: Int): Keycode = internal

end Keycode
