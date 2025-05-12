package bearlyb.scancode

enum Scancode(private[bearlyb] val internal: Int):
  case A extends Scancode(4)
  case B extends Scancode(5)
  case C extends Scancode(6)
  case D extends Scancode(7)
  case E extends Scancode(8)
  case F extends Scancode(9)
  case G extends Scancode(10)
  case H extends Scancode(11)
  case I extends Scancode(12)
  case J extends Scancode(13)
  case K extends Scancode(14)
  case L extends Scancode(15)
  case M extends Scancode(16)
  case N extends Scancode(17)
  case O extends Scancode(18)
  case P extends Scancode(19)
  case Q extends Scancode(20)
  case R extends Scancode(21)
  case S extends Scancode(22)
  case T extends Scancode(23)
  case U extends Scancode(24)
  case V extends Scancode(25)
  case W extends Scancode(26)
  case X extends Scancode(27)
  case Y extends Scancode(28)
  case Z extends Scancode(29)

  case K1 extends Scancode(30)
  case K2 extends Scancode(31)
  case K3 extends Scancode(32)
  case K4 extends Scancode(33)
  case K5 extends Scancode(34)
  case K6 extends Scancode(35)
  case K7 extends Scancode(36)
  case K8 extends Scancode(37)
  case K9 extends Scancode(38)
  case K0 extends Scancode(39)

  case Return extends Scancode(40)
  case Escape extends Scancode(41)
  case Backspace extends Scancode(42)
  case Tab extends Scancode(43)
  case Space extends Scancode(44)

  case Minus extends Scancode(45)
  case Equals extends Scancode(46)
  case LeftBracket extends Scancode(47)
  /** Located at the lower left of the return
  *   key on ISO keyboards and at the right end
  *   of the QWERTY row on ANSI keyboards.
  *   Produces REVERSE SOLIDUS (backslash) and
  *   VERTICAL LINE in a US layout, REVERSE
  *   SOLIDUS and VERTICAL LINE in a UK Mac
  *   layout, NUMBER SIGN and TILDE in a UK
  *   Windows layout, DOLLAR SIGN and POUND SIGN
  *   in a Swiss German layout, NUMBER SIGN and
  *   APOSTROPHE in a German layout, GRAVE
  *   ACCENT and POUND SIGN in a French Mac
  *   layout, and ASTERISK and MICRO SIGN in a
  *   French Windows layout.
  */
  case RightBracket extends Scancode(48)
  case Backslash extends Scancode(49)
  /** ISO USB keyboards actually use this code
  *   instead of 49 for the same key, but all
  *   OSes I've seen treat the two codes
  *   identically. So, as an implementor, unless
  *   your keyboard generates both of those
  *   codes and your OS treats them differently,
  *   you should generate case BACKSLASH
  * extends Scancode(instead)of this code. As a user, you
  *   should not rely on this code because SDL
  *   will never generate it with most (all?)
  *   keyboards.
  */
  case NonUSHash extends Scancode(50)
  case Semicolon extends Scancode(51)
  case Apostrophe extends Scancode(52)
  /** Located in the top left corner (on both ANSI
  *   and ISO keyboards). Produces GRAVE ACCENT and
  *   TILDE in a US Windows layout and in US and UK
  *   Mac layouts on ANSI keyboards, GRAVE ACCENT
  *   and NOT SIGN in a UK Windows layout, SECTION
  *   SIGN and PLUS-MINUS SIGN in US and UK Mac
  *   layouts on ISO keyboards, SECTION SIGN and
  *   DEGREE SIGN in a Swiss German layout (Mac:
  *   only on ISO keyboards), CIRCUMFLEX ACCENT and
  *   DEGREE SIGN in a German layout (Mac: only on
  *   ISO keyboards), SUPERSCRIPT TWO and TILDE in a
  *   French Windows layout, COMMERCIAL AT and
  *   NUMBER SIGN in a French Mac layout on ISO
  *   keyboards, and LESS-THAN SIGN and GREATER-THAN
  *   SIGN in a Swiss German, German, or French Mac
  *   layout on ANSI keyboards.
  */
  case Grave extends Scancode(53)
  case Comma extends Scancode(54)
  case Period extends Scancode(55)
  case Slash extends Scancode(56)

  case Capslock extends Scancode(57)

  case F1 extends Scancode(58)
  case F2 extends Scancode(59)
  case F3 extends Scancode(60)
  case F4 extends Scancode(61)
  case F5 extends Scancode(62)
  case F6 extends Scancode(63)
  case F7 extends Scancode(64)
  case F8 extends Scancode(65)
  case F9 extends Scancode(66)
  case F10 extends Scancode(67)
  case F11 extends Scancode(68)
  case F12 extends Scancode(69)

  case Printscreen extends Scancode(70)
  case Scrolllock extends Scancode(71)
  case Pause extends Scancode(72)
  /** insert on PC, help on some Mac keyboards (but
   does send code 73, not 117) */
  case Insert extends Scancode(73)
  case Home extends Scancode(74)
  case PageUp extends Scancode(75)
  case Delete extends Scancode(76)
  case End extends Scancode(77)
  case PageDown extends Scancode(78)
  case Right extends Scancode(79)
  case Left extends Scancode(80)
  case Down extends Scancode(81)
  case Up extends Scancode(82)

  case NumlockClear extends Scancode(83) /**< num lock on PC, clear on Mac keyboards
                                    */
  case KpDivide extends Scancode(84)
  case KpMultiply extends Scancode(85)
  case KpMinus extends Scancode(86)
  case KpPlus extends Scancode(87)
  case KpEnter extends Scancode(88)
  case Kp1 extends Scancode(89)
  case Kp2 extends Scancode(90)
  case Kp3 extends Scancode(91)
  case Kp4 extends Scancode(92)
  case Kp5 extends Scancode(93)
  case Kp6 extends Scancode(94)
  case Kp7 extends Scancode(95)
  case Kp8 extends Scancode(96)
  case Kp9 extends Scancode(97)
  case Kp0 extends Scancode(98)
  case KpPeriod extends Scancode(99)

  /** This is the additional key that ISO
  *   keyboards have over ANSI ones,
  *   located between left shift and Y.
  *   Produces GRAVE ACCENT and TILDE in a
  *   US or UK Mac layout, REVERSE SOLIDUS
  *   (backslash) and VERTICAL LINE in a
  *   US or UK Windows layout, and
  *   LESS-THAN SIGN and GREATER-THAN SIGN
  *   in a Swiss German, German, or French
  *   layout. */
  case NonUSBackslash extends Scancode(100)
  case Application extends Scancode(101) /**< windows contextual menu, compose */
  case Power extends Scancode(102) /**< The USB document says this is a status flag,
                              *   not a physical key - but some Mac keyboards
                              *   do have a power key. */
  case KpEquals extends Scancode(103)
  case F13 extends Scancode(104)
  case F14 extends Scancode(105)
  case F15 extends Scancode(106)
  case F16 extends Scancode(107)
  case F17 extends Scancode(108)
  case F18 extends Scancode(109)
  case F19 extends Scancode(110)
  case F20 extends Scancode(111)
  case F21 extends Scancode(112)
  case F22 extends Scancode(113)
  case F23 extends Scancode(114)
  case F24 extends Scancode(115)
  case Execute extends Scancode(116)
  case Help extends Scancode(117)    /**< AL Integrated Help Center */
  case Menu extends Scancode(118)    /**< Menu (show menu) */
  case Select extends Scancode(119)
  case Stop extends Scancode(120)    /**< AC Stop */
  case Again extends Scancode(121)   /**< AC Redo/Repeat */
  case Undo extends Scancode(122)    /**< AC Undo */
  case Cut extends Scancode(123)     /**< AC Cut */
  case Copy extends Scancode(124)    /**< AC Copy */
  case Paste extends Scancode(125)   /**< AC Paste */
  case Find extends Scancode(126)    /**< AC Find */
  case Mute extends Scancode(127)
  case VolumeUp extends Scancode(128)
  case VolumeDown extends Scancode(129)
  case KpComma extends Scancode(133)
  case KpEqualsas400 extends Scancode(134)

  case International1 extends Scancode(135) /**< used on Asian keyboards, see
                                            footnotes in USB doc */
  case International2 extends Scancode(136)
  case International3 extends Scancode(137) /**< Yen */
  case International4 extends Scancode(138)
  case International5 extends Scancode(139)
  case International6 extends Scancode(140)
  case International7 extends Scancode(141)
  case International8 extends Scancode(142)
  case International9 extends Scancode(143)
  case Lang1 extends Scancode(144) /**< Hangul/English toggle */
  case Lang2 extends Scancode(145) /**< Hanja conversion */
  case Lang3 extends Scancode(146) /**< Katakana */
  case Lang4 extends Scancode(147) /**< Hiragana */
  case Lang5 extends Scancode(148) /**< Zenkaku/Hankaku */
  case Lang6 extends Scancode(149) /**< reserved */
  case Lang7 extends Scancode(150) /**< reserved */
  case Lang8 extends Scancode(151) /**< reserved */
  case Lang9 extends Scancode(152) /**< reserved */

  case AltErase extends Scancode(153)    /**< Erase-Eaze */
  case SysReq extends Scancode(154)
  case Cancel extends Scancode(155)      /**< AC Cancel */
  case Clear extends Scancode(156)
  case Prior extends Scancode(157)
  case Return2 extends Scancode(158)
  case Separator extends Scancode(159)
  case Out extends Scancode(160)
  case Oper extends Scancode(161)
  case ClearAgain extends Scancode(162)
  case Crsel extends Scancode(163)
  case Exsel extends Scancode(164)

  case Kp00 extends Scancode(176)
  case Kp000 extends Scancode(177)
  case ThousandsSeparator extends Scancode(178)
  case DecimalSeparator extends Scancode(179)
  case CurrencyUnit extends Scancode(180)
  case CurrencySubunit extends Scancode(181)
  case KpLeftParen extends Scancode(182)
  case KpRightParen extends Scancode(183)
  case KpLeftBrace extends Scancode(184)
  case KpRightBrace extends Scancode(185)
  case KpTab extends Scancode(186)
  case KpBackspace extends Scancode(187)
  case KpA extends Scancode(188)
  case KpB extends Scancode(189)
  case KpC extends Scancode(190)
  case KpD extends Scancode(191)
  case KpE extends Scancode(192)
  case KpF extends Scancode(193)
  case KpXor extends Scancode(194)
  case KpPower extends Scancode(195)
  case KpPercent extends Scancode(196)
  case KpLess extends Scancode(197)
  case KpGreater extends Scancode(198)
  case KpAmpersand extends Scancode(199)
  case KpDblampersand extends Scancode(200)
  case KpVerticalbar extends Scancode(201)
  case KpDblverticalbar extends Scancode(202)
  case KpColon extends Scancode(203)
  case KpHash extends Scancode(204)
  case KpSpace extends Scancode(205)
  case KpAt extends Scancode(206)
  case KpExclam extends Scancode(207)
  case KpMemstore extends Scancode(208)
  case KpMemrecall extends Scancode(209)
  case KpMemclear extends Scancode(210)
  case KpMemadd extends Scancode(211)
  case KpMemsubtract extends Scancode(212)
  case KpMemmultiply extends Scancode(213)
  case KpMemdivide extends Scancode(214)
  case KpPlusminus extends Scancode(215)
  case KpClear extends Scancode(216)
  case KpClearentry extends Scancode(217)
  case KpBinary extends Scancode(218)
  case KpOctal extends Scancode(219)
  case KpDecimal extends Scancode(220)
  case KpHexadecimal extends Scancode(221)

  case LCtrl extends Scancode(224)
  case LShift extends Scancode(225)
  /**< alt, option */
  case LAlt extends Scancode(226)
  /**< windows, command (apple), meta */
  case LGUI extends Scancode(227)
  case RCtrl extends Scancode(228)
  case RShift extends Scancode(229)
  /**< alt gr, option */
  case Ralt extends Scancode(230)
  /**< windows, command (apple), meta */
  case RGUI extends Scancode(231)

  /**< I'm not sure if this is really not covered
   *   by any of the above, but since there's a
   *   special SDL_KMOD_MODE for it I'm adding it here
   */
  case Mode extends Scancode(257)

  /**< Sleep */
  case Sleep extends Scancode(258)
  /**< Wake */
  case Wake extends Scancode(259)

  /**< Channel Increment */
  case ChannelIncrement extends Scancode(260)
  /**< Channel Decrement */
  case ChannelDecrement extends Scancode(261)

  /**< Play */
  case MediaPlay extends Scancode(262)
  /**< Pause */
  case MediaPause extends Scancode(263)
  /**< Record */
  case MediaRecord extends Scancode(264)
  /**< Fast Forward */
  case MediaFastForward extends Scancode(265)
  /**< Rewind */
  case MediaRewind extends Scancode(266)
  /**< Next Track */
  case MediaNextTrack extends Scancode(267)
  /**< Previous Track */
  case MediaPreviousTrack extends Scancode(268)
  /**< Stop */
  case MediaStop extends Scancode(269)
  /**< Eject */
  case MediaEject extends Scancode(270)
  /**< Play / Pause */
  case MediaPlayPause extends Scancode(271)
  /* Media Select */
  case MediaSelect extends Scancode(272)

  /**< AC New */
  case AcNew extends Scancode(273)
  /**< AC Open */
  case AcOpen extends Scancode(274)
  /**< AC Close */
  case AcClose extends Scancode(275)
  /**< AC Exit */
  case AcExit extends Scancode(276)
  /**< AC Save */
  case AcSave extends Scancode(277)
  /**< AC Print */
  case AcPrint extends Scancode(278)
  /**< AC Properties */
  case AcProperties extends Scancode(279)

  /**< AC Search */
  case AcSearch extends Scancode(280)
  /**< AC Home */
  case AcHome extends Scancode(281)
  /**< AC Back */
  case AcBack extends Scancode(282)
  /**< AC Forward */
  case AcForward extends Scancode(283)
  /**< AC Stop */
  case AcStop extends Scancode(284)
  /**< AC Refresh */
  case AcRefresh extends Scancode(285)
  /**< AC Bookmarks */
  case AcBookmarks extends Scancode(286)

  /*
   *  \name Mobile keys
   *
   *  These are values that are often used on mobile phones.
   */

  /**< Usually situated below the display on phones and
    used as a multi-function feature key for selecting
    a software defined function shown on the bottom left
    of the display. */
  case SoftLeft extends Scancode(287)
  /**< Usually situated below the display on phones and
    used as a multi-function feature key for selecting
    a software defined function shown on the bottom right
    of the display. */
  case SoftRight extends Scancode(288)
  case Call extends Scancode(289) /**< Used for accepting phone calls. */
  case Endcall extends Scancode(290) /**< Used for rejecting phone calls. */

  case Dynamic(code: Int) extends Scancode(code)
end Scancode