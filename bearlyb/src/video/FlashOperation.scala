package bearlyb.video

/** Cancel - Cancel any window flash state Briefly - Flash the window briefly to
  * get attention UntilFocused - Flash the window until it gets focus
  */
enum FlashOperation:
  case Cancel, Briefly, UntilFocused

  private[bearlyb] def internal: Int = ordinal
