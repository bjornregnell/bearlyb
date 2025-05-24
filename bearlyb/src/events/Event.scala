package bearlyb.events

import scala.concurrent.duration.Deadline
import bearlyb.video.{DisplayID, WindowID}
import bearlyb.keyboard.KeyboardID
import bearlyb.scancode.Scancode
import bearlyb.keycode.{Keycode, Keymod}
import bearlyb.mouse, mouse.MouseID
import bearlyb.joystick.{JoystickID, HatPosition}
import bearlyb.power.PowerState
import bearlyb.gamepad
import bearlyb.sensor.SensorData
import bearlyb.touch
import bearlyb.pen.{PenID, PenInputFlags, PenAxis}
import bearlyb.camera.CameraID

trait Event:
  def timestamp: Deadline
object Event:
  /** User-requested quit */
  case class Quit(timestamp: Deadline) extends Event
  case class Terminating(timestamp: Deadline) extends Event
  case class LowMemory(timestamp: Deadline) extends Event
  case class WillEnterBackground(timestamp: Deadline) extends Event
  case class DidEnterBackground(timestamp: Deadline) extends Event
  case class WillEnterForeground(timestamp: Deadline) extends Event
  case class DidEnterForeground(timestamp: Deadline) extends Event
  case class LocaleChanged(timestamp: Deadline) extends Event
  case class ThemeChanged(timestamp: Deadline) extends Event
  
  enum Display extends Event:
    def id: DisplayID

    case Orientation(timestamp: Deadline, id: DisplayID)
    case Added(timestamp: Deadline, id: DisplayID)
    case Removed(timestamp: Deadline, id: DisplayID)
    case Moved(timestamp: Deadline, id: DisplayID)
    case DesktopModeChanged(timestamp: Deadline, id: DisplayID)
    case CurrentModeChanged(timestamp: Deadline, id: DisplayID)
    case ContentScaleChanged(timestamp: Deadline, id: DisplayID)

  enum Window extends Event:
    def id: WindowID

    /** Window has been shown */
    case Shown(timestamp: Deadline, id: WindowID)

    /** Window has been hidden */
    case Hidden(timestamp: Deadline, id: WindowID)

    /** Window has been exposed and should be redrawn, and can be redrawn directly
      * from event watchers for this event
      */
    case Exposed(timestamp: Deadline, id: WindowID)

    /** Window has been moved to x, y */
    case Moved(timestamp: Deadline, id: WindowID, x: Int, y: Int)

    /** Window has been resized to w x h */
    case Resized(timestamp: Deadline, id: WindowID, w: Int, h: Int)

    /** The pixel size of the window has changed to pw x ph */
    case PixelSizeChanged(timestamp: Deadline, id: WindowID, pw: Int, ph: Int)

    /** The pixel size of a Metal view associated with the window has changed */
    case MetalViewResized(timestamp: Deadline, id: WindowID)

    /** Window has been minimized */
    case Minimized(timestamp: Deadline, id: WindowID)

    /** Window has been maximized */
    case Maximized(timestamp: Deadline, id: WindowID)

    /** Window has been restored to normal size and position */
    case Restored(timestamp: Deadline, id: WindowID)

    /** Window has gained mouse focus */
    case MouseEnter(timestamp: Deadline, id: WindowID)

    /** Window has lost mouse focus */
    case MouseLeave(timestamp: Deadline, id: WindowID)

    /** Window has gained keyboard focus */
    case FocusGained(timestamp: Deadline, id: WindowID)

    /** Window has lost keyboard focus */
    case FocusLost(timestamp: Deadline, id: WindowID)

    /** The window manager requests that the window be closed */
    case CloseRequested(timestamp: Deadline, id: WindowID)

    /** Window had a hit test that wasn't SDL_HITTEST_NORMAL */
    case HitTest(timestamp: Deadline, id: WindowID)

    /** The ICC profile of the window's display has changed */
    case ICCProfChanged(timestamp: Deadline, id: WindowID)

    /** Window has been moved to display `display` */
    case DisplayChanged(timestamp: Deadline, id: WindowID, display: DisplayID)

    /** Window display scale has been changed */
    case DisplayScaleChanged(timestamp: Deadline, id: WindowID)

    /** The window safe area has been changed */
    case SafeAreaChanged(timestamp: Deadline, id: WindowID)

    /** The window has been occluded */
    case Occluded(timestamp: Deadline, id: WindowID)

    /** The window has entered fullscreen mode */
    case EnterFullscreen(timestamp: Deadline, id: WindowID)

    /** The window has left fullscreen mode */
    case LeaveFullscreen(timestamp: Deadline, id: WindowID)

    /** The window with the associated ID is being or has been destroyed. If this
      * message is being handled in an event watcher, the window handle is still
      * valid and can still be used to retrieve any properties associated with the
      * window. Otherwise, the handle has already been destroyed and all resources
      * associated with it are invalid
      */
    case Destroyed(timestamp: Deadline, id: WindowID)

    /** Window HDR properties have changed */
    case HDRStateChanged(timestamp: Deadline, id: WindowID)

  enum Key extends Event:
    def windowID: WindowID
    def which: KeyboardID
    def scancode: Scancode
    def key: Keycode
    def mod: Set[Keymod]
    def down: Boolean
    def repeat: Boolean

    case Down(timestamp: Deadline, windowID: WindowID, which: KeyboardID, scancode: Scancode, key: Keycode, mod: Set[Keymod], down: Boolean, repeat: Boolean)
    case Up(timestamp: Deadline, windowID: WindowID, which: KeyboardID, scancode: Scancode, key: Keycode, mod: Set[Keymod], down: Boolean, repeat: Boolean)

  case class TextEditing(timestamp: Deadline, windowID: WindowID, text: String, start: Int, length: Int) extends Event
  case class TextInput(timestamp: Deadline, windowID: WindowID, text: String) extends Event
  case class KeymapChanged(timestamp: Deadline) extends Event
  case class KeyboardAdded(timestamp: Deadline, which: KeyboardID) extends Event
  case class KeyboardRemoved(timestamp: Deadline, which: KeyboardID) extends Event
  case class TextEditingCandidates(timestamp: Deadline, windowID: WindowID, candidates: Seq[String], selectedCandidate: Option[Int], horizontal: Boolean) extends Event

  enum Mouse extends Event:
    def which: MouseID

    case ButtonDown(timestamp: Deadline, windowID: WindowID, which: MouseID, button: Byte, down: Boolean, clicks: Byte, x: Float, y: Float)
    case ButtonUp(timestamp: Deadline, windowID: WindowID, which: MouseID, button: Byte, down: Boolean, clicks: Byte, x: Float, y: Float)
    case Motion(timestamp: Deadline, which: MouseID, state: Set[mouse.Button], x: Float, y: Float, xrel: Float, yrel: Float)
    case Added(timestamp: Deadline, which: MouseID)
    case Removed(timestamp: Deadline, which: MouseID)

  enum Joystick extends Event:
    def which: JoystickID

    case ButtonDown(timestamp: Deadline, which: JoystickID, button: Byte, down: Boolean)
    case ButtonUp(timestamp: Deadline, which: JoystickID, button: Byte, down: Boolean)
    case AxisMotion(timestamp: Deadline, which: JoystickID, axis: Byte, value: Int)
    case BallMotion(timestamp: Deadline, which: JoystickID, ball: Byte, xrel: Int, yrel: Int)
    case HatMotion(timestamp: Deadline, which: JoystickID, hat: Byte, value: HatPosition)
    case Added(timestamp: Deadline, which: JoystickID)
    case Removed(timestamp: Deadline, which: JoystickID)
    case BatteryUpdated(timestamp: Deadline, which: JoystickID, state: PowerState, percent: Int)
    case UpdateComplete(timestamp: Deadline, which: JoystickID)

  enum Gamepad extends Event:
    def which: JoystickID

    case ButtonDown(timestamp: Deadline, which: JoystickID, button: gamepad.Gamepad.Button, down: Boolean)
    case ButtonUp(timestamp: Deadline, which: JoystickID, button: gamepad.Gamepad.Button, down: Boolean)
    case AxisMotion(timestamp: Deadline, which: JoystickID, axis: gamepad.Gamepad.Axis, value: Int)
    case Added(timestamp: Deadline, which: JoystickID)
    case Removed(timestamp: Deadline, which: JoystickID)
    case Remapped(timestamp: Deadline, which: JoystickID)
    case TouchpadDown(timestamp: Deadline, which: JoystickID, touchpad: Int, finger: Int, x: Float, y: Float, pressure: Float)
    case TouchpadMotion(timestamp: Deadline, which: JoystickID, touchpad: Int, finger: Int, x: Float, y: Float, pressure: Float)
    case TouchpadUp(timestamp: Deadline, which: JoystickID, touchpad: Int, finger: Int, x: Float, y: Float, pressure: Float)
    case SensorUpdate(timestamp: Deadline, which: JoystickID, data: SensorData, sensorTimestamp: Long)
    case UpdateComplete(timestamp: Deadline, which: JoystickID)
    case SteamHandleUpdated(timestamp: Deadline, which: JoystickID)

  enum Finger extends Event:
    def touchID: touch.TouchID
    def fingerID: touch.FingerID
    def x: Float
    def y: Float
    def dx: Float
    def dy: Float
    def pressure: Float
    def windowID: WindowID

    case Down(timestamp: Deadline, touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)
    case Up(timestamp: Deadline, touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)
    case Motion(timestamp: Deadline, touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)
    case Canceled(timestamp: Deadline, touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)

  /** An event which lets you handle the clipboard
    * @param owner are we owning the clipboard? (internal update)
    * @param mimeTypes current mime types, a mime type is the kind of data contained in the clipboard. Examples of MIME-types are text/javascript or image/png.
    */
  case class ClipboardUpdate(timestamp: Deadline, owner: Boolean, mimeTypes: String*) extends Event

  enum Drop extends Event:
    def windowID: WindowID
    def source: Option[String]

    case File(timestamp: Deadline, windowID: WindowID, x: Float, y: Float, source: Option[String], filename: String)
    case Text(timestamp: Deadline, windowID: WindowID, x: Float, y: Float, source: Option[String], text: String)
    case Begin(timestamp: Deadline, windowID: WindowID, source: Option[String])
    case Complete(timestamp: Deadline, windowID: WindowID, x: Float, y: Float, source: Option[String])
    case Position(timestamp: Deadline, windowID: WindowID, x: Float, y: Float, source: Option[String])

  enum Pen extends Event:
    def windowID: WindowID
    def which: PenID
    
    case ProximityIn(timestamp: Deadline, windowID: WindowID, which: PenID)
    case ProximityOut(timestamp: Deadline, windowID: WindowID, which: PenID)
    case Down(timestamp: Deadline, windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float, eraser: Boolean, down: Boolean)
    case Up(timestamp: Deadline, windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float, eraser: Boolean, down: Boolean)
    case ButtonDown(timestamp: Deadline, windowID: WindowID, which: PenID, penState: Set[PenInputFlags])
    case ButtonUp(timestamp: Deadline, windowID: WindowID, which: PenID, penState: Set[PenInputFlags])
    case Motion(timestamp: Deadline, windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float)
    case Axis(timestamp: Deadline, windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float, axis: PenAxis, value: Float)

  enum CameraDevice extends Event:
    def which: CameraID
    
    case Added(timestamp: Deadline, which: CameraID)
    case Removed(timestamp: Deadline, which: CameraID)
    case Approved(timestamp: Deadline, which: CameraID)
    case Denied(timestamp: Deadline, which: CameraID)

  enum Render extends Event:
    def windowID: WindowID

    case TargetsReset(timestamp: Deadline, windowID: WindowID)
    case DeviceReset(timestamp: Deadline, windowID: WindowID)
    case DeviceLost(timestamp: Deadline, windowID: WindowID)
end Event