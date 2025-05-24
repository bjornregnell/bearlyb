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

trait Event(val timestamp: Deadline = Deadline.now)
object Event:
  /** User-requested quit */
  object Quit extends Event
  object Terminating extends Event
  object LowMemory extends Event
  object WillEnterBackground extends Event
  object DidEnterBackground extends Event
  object WillEnterForeground extends Event
  object DidEnterForeground extends Event
  object LocaleChanged extends Event
  object ThemeChanged extends Event
  
  enum Display extends Event:
    def id: DisplayID

    case Orientation(id: DisplayID)
    case Added(id: DisplayID)
    case Removed(id: DisplayID)
    case Moved(id: DisplayID)
    case DesktopModeChanged(id: DisplayID)
    case CurrentModeChanged(id: DisplayID)
    case ContentScaleChanged(id: DisplayID)

  enum Window extends Event:
    def id: WindowID

    /** Window has been shown */
    case Shown(id: WindowID)

    /** Window has been hidden */
    case Hidden(id: WindowID)

    /** Window has been exposed and should be redrawn, and can be redrawn directly
      * from event watchers for this event
      */
    case Exposed(id: WindowID)

    /** Window has been moved to x, y */
    case Moved(id: WindowID, x: Int, y: Int)

    /** Window has been resized to w x h */
    case Resized(id: WindowID, w: Int, h: Int)

    /** The pixel size of the window has changed to pw x ph */
    case PixelSizeChanged(id: WindowID, pw: Int, ph: Int)

    /** The pixel size of a Metal view associated with the window has changed */
    case MetalViewResized(id: WindowID)

    /** Window has been minimized */
    case Minimized(id: WindowID)

    /** Window has been maximized */
    case Maximized(id: WindowID)

    /** Window has been restored to normal size and position */
    case Restored(id: WindowID)

    /** Window has gained mouse focus */
    case MouseEnter(id: WindowID)

    /** Window has lost mouse focus */
    case MouseLeave(id: WindowID)

    /** Window has gained keyboard focus */
    case FocusGained(id: WindowID)

    /** Window has lost keyboard focus */
    case FocusLost(id: WindowID)

    /** The window manager requests that the window be closed */
    case CloseRequested(id: WindowID)

    /** Window had a hit test that wasn't SDL_HITTEST_NORMAL */
    case HitTest(id: WindowID)

    /** The ICC profile of the window's display has changed */
    case ICCProfChanged(id: WindowID)

    /** Window has been moved to display `display` */
    case DisplayChanged(id: WindowID, display: DisplayID)

    /** Window display scale has been changed */
    case DisplayScaleChanged(id: WindowID)

    /** The window safe area has been changed */
    case SafeAreaChanged(id: WindowID)

    /** The window has been occluded */
    case Occluded(id: WindowID)

    /** The window has entered fullscreen mode */
    case EnterFullscreen(id: WindowID)

    /** The window has left fullscreen mode */
    case LeaveFullscreen(id: WindowID)

    /** The window with the associated ID is being or has been destroyed. If this
      * message is being handled in an event watcher, the window handle is still
      * valid and can still be used to retrieve any properties associated with the
      * window. Otherwise, the handle has already been destroyed and all resources
      * associated with it are invalid
      */
    case Destroyed(id: WindowID)

    /** Window HDR properties have changed */
    case HDRStateChanged(id: WindowID)

  enum Key extends Event:
    def windowID: WindowID
    def which: KeyboardID
    def scancode: Scancode
    def key: Keycode
    def mod: Set[Keymod]
    def down: Boolean
    def repeat: Boolean

    case Down(windowID: WindowID, which: KeyboardID, scancode: Scancode, key: Keycode, mod: Set[Keymod], down: Boolean, repeat: Boolean)
    case Up(windowID: WindowID, which: KeyboardID, scancode: Scancode, key: Keycode, mod: Set[Keymod], down: Boolean, repeat: Boolean)

  case class TextEditing(windowID: WindowID, text: String, start: Int, length: Int) extends Event
  case class TextInput(windowID: WindowID, text: String) extends Event
  case object KeymapChanged extends Event
  case class KeyboardAdded(which: KeyboardID) extends Event
  case class KeyboardRemoved(which: KeyboardID) extends Event
  case class TextEditingCandidates(windowID: WindowID, candidates: Seq[String], selectedCandidate: Option[Int], horizontal: Boolean) extends Event

  enum Mouse extends Event:
    def which: MouseID

    case ButtonDown(windowID: WindowID, which: MouseID, button: Byte, down: Boolean, clicks: Byte, x: Float, y: Float)
    case ButtonUp(windowID: WindowID, which: MouseID, button: Byte, down: Boolean, clicks: Byte, x: Float, y: Float)
    case Motion(which: MouseID, state: Set[mouse.Button], x: Float, y: Float, xrel: Float, yrel: Float)
    case Added(which: MouseID)
    case Removed(which: MouseID)

  enum Joystick extends Event:
    def which: JoystickID

    case ButtonDown(which: JoystickID, button: Byte, down: Boolean)
    case ButtonUp(which: JoystickID, button: Byte, down: Boolean)
    case AxisMotion(which: JoystickID, axis: Byte, value: Int)
    case BallMotion(which: JoystickID, ball: Byte, xrel: Int, yrel: Int)
    case HatMotion(which: JoystickID, hat: Byte, value: HatPosition)
    case Added(which: JoystickID)
    case Removed(which: JoystickID)
    case BatteryUpdated(which: JoystickID, state: PowerState, percent: Int)
    case UpdateComplete(which: JoystickID)

  enum Gamepad extends Event:
    def which: JoystickID

    case ButtonDown(which: JoystickID, button: gamepad.Gamepad.Button, down: Boolean)
    case ButtonUp(which: JoystickID, button: gamepad.Gamepad.Button, down: Boolean)
    case AxisMotion(which: JoystickID, axis: gamepad.Gamepad.Axis, value: Int)
    case Added(which: JoystickID)
    case Removed(which: JoystickID)
    case Remapped(which: JoystickID)
    case TouchpadDown(which: JoystickID, touchpad: Int, finger: Int, x: Float, y: Float, pressure: Float)
    case TouchpadMotion(which: JoystickID, touchpad: Int, finger: Int, x: Float, y: Float, pressure: Float)
    case TouchpadUp(which: JoystickID, touchpad: Int, finger: Int, x: Float, y: Float, pressure: Float)
    case SensorUpdate(which: JoystickID, data: SensorData, sensorTimestamp: Long)
    case UpdateComplete(which: JoystickID)
    case SteamHandleUpdated(which: JoystickID)

  enum Finger extends Event:
    def touchID: touch.TouchID
    def fingerID: touch.FingerID
    def x: Float
    def y: Float
    def dx: Float
    def dy: Float
    def pressure: Float
    def windowID: WindowID

    case Down(touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)
    case Up(touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)
    case Motion(touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)
    case Canceled(touchID: touch.TouchID, fingerID: touch.FingerID, x: Float, y: Float, dx: Float, dy: Float, pressure: Float, windowID: WindowID)

  /** An event which lets you handle the clipboard
    * @param owner are we owning the clipboard? (internal update)
    * @param mimeTypes current mime types, a mime type is the kind of data contained in the clipboard. Examples of MIME-types are text/javascript or image/png.
    */
  case class ClipboardUpdate(owner: Boolean, mimeTypes: String*) extends Event

  enum Drop extends Event:
    def windowID: WindowID
    def source: Option[String]

    case File(windowID: WindowID, x: Float, y: Float, source: Option[String], filename: String)
    case Text(windowID: WindowID, x: Float, y: Float, source: Option[String], text: String)
    case Begin(windowID: WindowID, source: Option[String])
    case Complete(windowID: WindowID, x: Float, y: Float, source: Option[String])
    case Position(windowID: WindowID, x: Float, y: Float, source: Option[String])

  enum Pen extends Event:
    def windowID: WindowID
    def which: PenID
    
    case ProximityIn(windowID: WindowID, which: PenID)
    case ProximityOut(windowID: WindowID, which: PenID)
    case Down(windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float, eraser: Boolean, down: Boolean)
    case Up(windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float, eraser: Boolean, down: Boolean)
    case ButtonDown(windowID: WindowID, which: PenID, penState: Set[PenInputFlags])
    case ButtonUp(windowID: WindowID, which: PenID, penState: Set[PenInputFlags])
    case Motion(windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float)
    case Axis(windowID: WindowID, which: PenID, penState: Set[PenInputFlags], x: Float, y: Float, axis: PenAxis, value: Float)

  enum CameraDevice extends Event:
    def which: CameraID
    
    case Added(which: CameraID)
    case Removed(which: CameraID)
    case Approved(which: CameraID)
    case Denied(which: CameraID)

  enum Render extends Event:
    def windowID: WindowID

    case TargetsReset(windowID: WindowID)
    case DeviceReset(windowID: WindowID)
    case DeviceLost(windowID: WindowID)
end Event