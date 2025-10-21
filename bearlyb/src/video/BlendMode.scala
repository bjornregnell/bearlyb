package bearlyb.video

import org.lwjgl.sdl.SDLBlendMode.*

opaque type BlendMode = Int

object BlendMode:

  /** no blending: dstRGBA = srcRGBA */
  val None: BlendMode = SDL_BLENDMODE_NONE

  /** alpha blending: dstRGB = (srcRGB * srcA) + (dstRGB * (1-srcA)), dstA =
    * srcA + (dstA * (1-srcA))
    */
  val Blend: BlendMode = SDL_BLENDMODE_BLEND

  /** pre-multiplied alpha blending: dstRGBA = srcRGBA + (dstRGBA * (1-srcA)) */
  val BlendPremultiplied: BlendMode = SDL_BLENDMODE_BLEND_PREMULTIPLIED

  /** additive blending: dstRGB = (srcRGB * srcA) + dstRGB, dstA = dstA */
  val Add: BlendMode = SDL_BLENDMODE_ADD

  /** pre-multiplied additive blending: dstRGB = srcRGB + dstRGB, dstA = dstA */
  val AddPremultiplied: BlendMode = SDL_BLENDMODE_ADD_PREMULTIPLIED

  /** color modulate: dstRGB = srcRGB * dstRGB, dstA = dstA */
  val Mod: BlendMode = SDL_BLENDMODE_MOD

  /** color multiply: dstRGB = (srcRGB * dstRGB) + (dstRGB * (1-srcA)), dstA =
    * dstA
    */
  val Mul: BlendMode = SDL_BLENDMODE_MUL

  val Invalid: BlendMode = SDL_BLENDMODE_INVALID

  private[bearlyb] def fromInternal(internal: Int): BlendMode = internal

  extension (blendMode: BlendMode)
    private[bearlyb] def internal: Int = blendMode

end BlendMode
