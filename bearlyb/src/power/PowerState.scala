package bearlyb.power

enum PowerState:
  case Error, Unknown, OnBattery, NoBattery, Charging, Charged

  private[bearlyb] def internal: Int =
    ordinal - 1

object PowerState:
  private[bearlyb] def fromInternal(internal: Int): PowerState =
    PowerState.fromOrdinal(internal + 1)