package bearlyb

import org.lwjgl.sdl.SDLError.SDL_GetError
private[bearlyb] inline val NullPtr = org.lwjgl.system.MemoryUtil.NULL

private[bearlyb] def sdlError(): Nothing =
  sys.error(s"SDL Error: ${SDL_GetError()}")

extension (notError: Boolean)
  private[bearlyb] def sdlErrorCheck[T](value: T = ()): T =    
    if notError then value else sdlError()

extension (ptr: Long)
  private[bearlyb] def sdlCreationCheck(): Long =
    if ptr != NullPtr then ptr else sdlError()

extension [T](obj: T)
  private[bearlyb] def sdlCreationCheck(): T =
    if obj != null then obj else sdlError()