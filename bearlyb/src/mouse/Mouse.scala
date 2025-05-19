package bearlyb.mouse

object Mouse:
  enum Button:
    case Left, Middle, Right, X1, X2

    private[bearlyb] def internal: Int = this.ordinal + 1

  object Button:
    private[bearlyb] def fromInternal(internal: Int): Button =
      Button.fromOrdinal(internal - 1)