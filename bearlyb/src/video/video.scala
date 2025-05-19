package bearlyb.video

type DisplayID = Int
/** The value of 0 is an invalid ID. */
type WindowID = Int

def systemTheme: SystemTheme =
  SystemTheme.get