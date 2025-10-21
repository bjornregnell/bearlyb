package bearlyb.pixels

opaque type RawColor = Int

object RawColor:
  private[bearlyb] def apply(inner: Int): RawColor = inner

  extension (color: RawColor) private[bearlyb] def internal: Int = color
