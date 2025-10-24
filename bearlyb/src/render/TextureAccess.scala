package bearlyb.render

enum TextureAccess:
  case Static, Streaming, Target

  private[bearlyb] def internal: Int = ordinal

object TextureAccess:

  private[bearlyb] def fromInternal(internal: Int): TextureAccess =
    TextureAccess.fromOrdinal(internal)
