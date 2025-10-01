package bearlyb.gamepad

object Gamepad:

  enum Button:

    case Invalid, South, East, West, North, Back, Guide, Start, LeftStick,
      RightStick, LeftShoulder, RightShoulder, DpadUp, DpadDown, DpadLeft,
      DpadRight,
      /** Additional button (e.g. Xbox Series X share button, PS5 microphone
        * button, Nintendo Switch Pro capture button, Amazon Luna microphone
        * button, Google Stadia capture button)
        */
      Misc1, RightPaddle1, LeftPaddle1, RightPaddle2, LeftPaddle2, Touchpad,
      Misc2, Misc3, Misc4, Misc5, Misc6

    private[bearlyb] def internal: Int = ordinal - 1

  end Button

  object Button:

    private[bearlyb] def fromInternal(value: Int): Button = Button
      .fromOrdinal(value + 1)

  enum Axis:

    case Invalid, LeftX, LeftY, RightX, RightY, LeftTrigger, RightTrigger

    private[bearlyb] def internal: Int = ordinal - 1

  object Axis:

    private[bearlyb] def fromInternal(value: Int): Axis = Axis
      .fromOrdinal(value + 1)

end Gamepad
