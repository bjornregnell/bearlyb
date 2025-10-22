package bearlyb

export initialize.{init, quit, Flags as Init}
export video.{Window, SystemTheme, systemTheme}
export render.{Renderer, createWindowAndRenderer, Texture}
export events.Event
export keycode.{Keycode, Keymod}
export rect.{Point, Rect}

case class BearlybException(msg: String) extends RuntimeException(msg)
