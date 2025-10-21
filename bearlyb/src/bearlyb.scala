package bearlyb

export initialize.init
export video.{Window, SystemTheme, systemTheme}
export render.{Renderer, createWindowAndRenderer, Texture}

case class BearlybException(msg: String) extends RuntimeException(msg)
