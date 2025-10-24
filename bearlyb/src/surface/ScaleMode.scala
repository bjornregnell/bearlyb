package bearlyb.surface

enum ScaleMode:
  /** Nearest pixel sampling */
  case Nearest

  /** Linear filtering */
  case Linear

  /** Nearest pixel sampling with improved filtering for pixelart */
  // case PixelArt

end ScaleMode

object ScaleMode:
  given ScaleMode = ScaleMode.Linear
