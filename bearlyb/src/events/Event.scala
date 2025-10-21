package bearlyb.events

import scala.concurrent.duration.*
import bearlyb.video.{DisplayID, WindowID}
import bearlyb.keyboard.KeyboardID
import bearlyb.scancode.Scancode
import bearlyb.keycode.*
import bearlyb.mouse, mouse.MouseID
import bearlyb.joystick.{JoystickID, HatPosition}
import bearlyb.power.PowerState
import bearlyb.gamepad
import bearlyb.sensor.SensorData
import bearlyb.touch
import bearlyb.pen.{PenID, PenInput, PenAxis, toPenInputSet}
import bearlyb.camera.CameraID
import org.lwjgl.sdl.*
import org.lwjgl.sdl.SDLEvents.*
import org.lwjgl.PointerBuffer
import bearlyb.mouse.toMouseButtonSet
import scala.util.Using
import org.lwjgl.system.MemoryStack.*
import java.util as ju

extension (internal: SDL_Event)

  private[bearlyb] inline def deadline: Deadline =
    Deadline(internal.common().timestamp().nanos)

trait Event:
  def timestamp: Deadline

object Event:

  def pollEvents: Iterator[Event] = new Iterator[Event]:
    private var eventBuffer: Option[SDL_Event] = pollEvent

    private def pollEvent: Option[SDL_Event] = Using(stackPush()): stack =>
      val event = SDL_Event.malloc(stack)
      if SDL_PollEvent(event) then Some(event) else None
    .get

    override def next(): Event = (eventBuffer, pollEvent) match
      case (Some(oldEvent), Some(newEvent)) =>
        eventBuffer = Some(newEvent)
        Event.fromInternal(oldEvent)
      case (Some(oldEvent), None) =>
        eventBuffer = None
        Event.fromInternal(oldEvent)
      case (None, _) =>
        throw ju.NoSuchElementException("No more events to handle")

    override def hasNext: Boolean = eventBuffer.nonEmpty
  end pollEvents

  private[bearlyb] def fromInternal(internal: SDL_Event): Event =
    internal.`type`() match
      case SDL_EVENT_QUIT                  => Quit(internal.deadline)
      case SDL_EVENT_TERMINATING           => Terminating(internal.deadline)
      case SDL_EVENT_LOW_MEMORY            => LowMemory(internal.deadline)
      case SDL_EVENT_WILL_ENTER_BACKGROUND =>
        WillEnterBackground(internal.deadline)
      case SDL_EVENT_DID_ENTER_BACKGROUND =>
        DidEnterBackground(internal.deadline)
      case SDL_EVENT_WILL_ENTER_FOREGROUND =>
        WillEnterForeground(internal.deadline)
      case SDL_EVENT_LOCALE_CHANGED       => LocaleChanged(internal.deadline)
      case SDL_EVENT_SYSTEM_THEME_CHANGED =>
        SystemThemeChanged(internal.deadline)

      // Display events
      case SDL_EVENT_DISPLAY_ORIENTATION => Display
          .Orientation(internal.deadline, internal.display.displayID)
      case SDL_EVENT_DISPLAY_ADDED => Display
          .Added(internal.deadline, internal.display.displayID)
      case SDL_EVENT_DISPLAY_REMOVED => Display
          .Removed(internal.deadline, internal.display.displayID)
      case SDL_EVENT_DISPLAY_MOVED => Display
          .Moved(internal.deadline, internal.display.displayID)
      case SDL_EVENT_DISPLAY_DESKTOP_MODE_CHANGED => Display
          .DesktopModeChanged(internal.deadline, internal.display.displayID)
      case SDL_EVENT_DISPLAY_CURRENT_MODE_CHANGED => Display
          .CurrentModeChanged(internal.deadline, internal.display.displayID)
      case SDL_EVENT_DISPLAY_CONTENT_SCALE_CHANGED => Display
          .ContentScaleChanged(internal.deadline, internal.display.displayID)

      // Window events
      case SDL_EVENT_WINDOW_SHOWN => Window
          .Shown(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_HIDDEN => Window
          .Hidden(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_EXPOSED => Window
          .Exposed(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_MOVED => Window.Moved(
          internal.deadline, internal.window.windowID, internal.window.data1,
          internal.window.data2
        )
      case SDL_EVENT_WINDOW_RESIZED => Window.Resized(
          internal.deadline, internal.window.windowID, internal.window.data1,
          internal.window.data2
        )
      case SDL_EVENT_WINDOW_PIXEL_SIZE_CHANGED => Window.Resized(
          internal.deadline, internal.window.windowID, internal.window.data1,
          internal.window.data2
        )
      case SDL_EVENT_WINDOW_METAL_VIEW_RESIZED => Window
          .MetalViewResized(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_MINIMIZED => Window
          .Minimized(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_MAXIMIZED => Window
          .Maximized(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_RESTORED => Window
          .Restored(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_MOUSE_ENTER => Window
          .MouseEnter(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_MOUSE_LEAVE => Window
          .MouseLeave(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_FOCUS_GAINED => Window
          .FocusGained(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_FOCUS_LOST => Window
          .FocusLost(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_CLOSE_REQUESTED => Window
          .CloseRequested(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_HIT_TEST => Window
          .HitTest(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_ICCPROF_CHANGED => Window
          .ICCProfChanged(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_DISPLAY_CHANGED => Window.DisplayChanged(
          internal.deadline, internal.window.windowID, internal.window.data1
        )
      case SDL_EVENT_WINDOW_SAFE_AREA_CHANGED => Window
          .SafeAreaChanged(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_OCCLUDED => Window
          .Occluded(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_ENTER_FULLSCREEN => Window
          .EnterFullscreen(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_LEAVE_FULLSCREEN => Window
          .LeaveFullscreen(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_DESTROYED => Window
          .Destroyed(internal.deadline, internal.window.windowID)
      case SDL_EVENT_WINDOW_HDR_STATE_CHANGED => Window
          .HDRStateChanged(internal.deadline, internal.window.windowID)

      // Key events
      case SDL_EVENT_KEY_DOWN => Key.Down(
          internal.deadline,
          internal.key.windowID,
          internal.key.which,
          Scancode.fromInternal(internal.key().scancode()),
          Keycode.fromInternal(internal.key().key()),
          internal.key.mod.toKeymodSet,
          internal.key.down,
          internal.key.repeat
        )
      case SDL_EVENT_KEY_UP => Key.Up(
          internal.deadline,
          internal.key.windowID,
          internal.key.which,
          Scancode.fromInternal(internal.key().scancode()),
          Keycode.fromInternal(internal.key().key()),
          internal.key.mod.toKeymodSet,
          internal.key.down,
          internal.key.repeat
        )

      case SDL_EVENT_TEXT_EDITING => TextEditing(
          internal.deadline, internal.edit.windowID, internal.edit.textString,
          internal.edit.start, internal.edit.length
        )

      case SDL_EVENT_TEXT_INPUT => TextInput(
          internal.deadline, internal.text.windowID, internal.text.textString
        )

      case SDL_EVENT_KEYMAP_CHANGED => KeymapChanged(internal.deadline)
      case SDL_EVENT_KEYBOARD_ADDED =>
        KeyboardAdded(internal.deadline, internal.kdevice.which)
      case SDL_EVENT_KEYBOARD_REMOVED =>
        KeyboardAdded(internal.deadline, internal.kdevice.which)

      case SDL_EVENT_TEXT_EDITING_CANDIDATES =>
        val candidateBuffer: Option[PointerBuffer] =
          Option(internal.edit_candidates.candidates)
        val candidateSeq: Seq[String] = candidateBuffer match
          case None         => Seq.empty
          case Some(buffer) => Seq.tabulate(
              internal.edit_candidates.num_candidates
            )(buffer.getStringUTF8)
        val selectedCandidate =
          internal.edit_candidates.selected_candidate match
            case -1 => None
            case c  => Some(c)
        TextEditingCandidates(
          internal.deadline, internal.edit_candidates.windowID, candidateSeq,
          selectedCandidate, internal.edit_candidates.horizontal
        )

      case SDL_EVENT_MOUSE_BUTTON_DOWN => Mouse.ButtonDown(
          internal.deadline,
          internal.button.windowID,
          internal.button.which,
          mouse.Button.fromInternal(internal.button.button),
          internal.button.down,
          internal.button.clicks,
          internal.button.x,
          internal.button.y
        )
      case SDL_EVENT_MOUSE_BUTTON_UP => Mouse.ButtonUp(
          internal.deadline,
          internal.button.windowID,
          internal.button.which,
          mouse.Button.fromInternal(internal.button.button),
          internal.button.down,
          internal.button.clicks,
          internal.button.x,
          internal.button.y
        )

      case SDL_EVENT_MOUSE_MOTION => Mouse.Motion(
          internal.deadline,
          internal.motion.which,
          internal.motion.state.toMouseButtonSet,
          internal.motion.x,
          internal.motion.y,
          internal.motion.xrel,
          internal.motion.yrel
        )

      case SDL_EVENT_MOUSE_ADDED => Mouse
          .Added(internal.deadline, internal.mdevice.which)

      case SDL_EVENT_MOUSE_REMOVED => Mouse
          .Removed(internal.deadline, internal.mdevice.which)

      case SDL_EVENT_JOYSTICK_BUTTON_DOWN => Joystick.ButtonDown(
          internal.deadline, internal.jbutton.which, internal.jbutton.button,
          internal.jbutton.down
        )

      case SDL_EVENT_JOYSTICK_BUTTON_UP => Joystick.ButtonUp(
          internal.deadline, internal.jbutton.which, internal.jbutton.button,
          internal.jbutton.down
        )

      case SDL_EVENT_JOYSTICK_AXIS_MOTION => Joystick.AxisMotion(
          internal.deadline, internal.jaxis.which, internal.jaxis.axis,
          internal.jaxis.value
        )

      case SDL_EVENT_JOYSTICK_BALL_MOTION => Joystick.BallMotion(
          internal.deadline, internal.jball.which, internal.jball.ball,
          internal.jball.xrel, internal.jball.yrel
        )

      case SDL_EVENT_JOYSTICK_HAT_MOTION => Joystick.HatMotion(
          internal.deadline, internal.jhat.which, internal.jhat.hat,
          HatPosition.fromInternal(internal.jhat.value)
        )

      case SDL_EVENT_JOYSTICK_ADDED => Joystick
          .Added(internal.deadline, internal.jdevice.which)

      case SDL_EVENT_JOYSTICK_REMOVED => Joystick
          .Removed(internal.deadline, internal.jdevice.which)

      case SDL_EVENT_JOYSTICK_BATTERY_UPDATED => Joystick.BatteryUpdated(
          internal.deadline, internal.jbattery.which,
          PowerState.fromInternal(internal.jbattery.state),
          internal.jbattery.percent
        )

      case SDL_EVENT_JOYSTICK_UPDATE_COMPLETE => Joystick
          .UpdateComplete(internal.deadline, internal.jdevice.which)

      case SDL_EVENT_GAMEPAD_BUTTON_DOWN => Gamepad.ButtonDown(
          internal.deadline, internal.gbutton.which,
          gamepad.Gamepad.Button.fromInternal(internal.gbutton.button),
          internal.gbutton.down
        )

      case SDL_EVENT_GAMEPAD_BUTTON_UP => Gamepad.ButtonUp(
          internal.deadline, internal.gbutton.which,
          gamepad.Gamepad.Button.fromInternal(internal.gbutton.button),
          internal.gbutton.down
        )

      case SDL_EVENT_GAMEPAD_AXIS_MOTION => Gamepad.AxisMotion(
          internal.deadline, internal.gaxis.which,
          gamepad.Gamepad.Axis.fromInternal(internal.gaxis.axis),
          internal.gaxis.value
        )

      case SDL_EVENT_GAMEPAD_ADDED => Gamepad
          .Added(internal.deadline, internal.gdevice.which)
      case SDL_EVENT_GAMEPAD_REMOVED => Gamepad
          .Removed(internal.deadline, internal.gdevice.which)
      case SDL_EVENT_GAMEPAD_REMAPPED => Gamepad
          .Remapped(internal.deadline, internal.gdevice.which)

      case SDL_EVENT_GAMEPAD_TOUCHPAD_DOWN => Gamepad.TouchpadDown(
          internal.deadline,
          internal.gtouchpad.which,
          internal.gtouchpad.touchpad,
          internal.gtouchpad.finger,
          internal.gtouchpad.x,
          internal.gtouchpad.y,
          internal.gtouchpad.pressure
        )

      case SDL_EVENT_GAMEPAD_TOUCHPAD_MOTION => Gamepad.TouchpadMotion(
          internal.deadline,
          internal.gtouchpad.which,
          internal.gtouchpad.touchpad,
          internal.gtouchpad.finger,
          internal.gtouchpad.x,
          internal.gtouchpad.y,
          internal.gtouchpad.pressure
        )

      case SDL_EVENT_GAMEPAD_TOUCHPAD_UP => Gamepad.TouchpadUp(
          internal.deadline,
          internal.gtouchpad.which,
          internal.gtouchpad.touchpad,
          internal.gtouchpad.finger,
          internal.gtouchpad.x,
          internal.gtouchpad.y,
          internal.gtouchpad.pressure
        )

      case SDL_EVENT_GAMEPAD_SENSOR_UPDATE => Gamepad.SensorUpdate(
          internal.deadline, internal.gsensor.which,
          SensorData.fromInternal(
            internal.gsensor.sensor, internal.gsensor.data
          ), internal.gsensor.sensor_timestamp
        )

      case SDL_EVENT_GAMEPAD_UPDATE_COMPLETE => Gamepad
          .UpdateComplete(internal.deadline, internal.gdevice.which)

      case SDL_EVENT_GAMEPAD_STEAM_HANDLE_UPDATED => Gamepad
          .SteamHandleUpdated(internal.deadline, internal.gdevice.which)

      case SDL_EVENT_FINGER_DOWN => Finger.Down(
          internal.deadline,
          internal.tfinger.touchID,
          internal.tfinger.fingerID,
          internal.tfinger.x,
          internal.tfinger.y,
          internal.tfinger.dx,
          internal.tfinger.dy,
          internal.tfinger.pressure,
          internal.tfinger.windowID
        )

      case SDL_EVENT_FINGER_UP => Finger.Up(
          internal.deadline,
          internal.tfinger.touchID,
          internal.tfinger.fingerID,
          internal.tfinger.x,
          internal.tfinger.y,
          internal.tfinger.dx,
          internal.tfinger.dy,
          internal.tfinger.pressure,
          internal.tfinger.windowID
        )

      case SDL_EVENT_FINGER_MOTION => Finger.Motion(
          internal.deadline,
          internal.tfinger.touchID,
          internal.tfinger.fingerID,
          internal.tfinger.x,
          internal.tfinger.y,
          internal.tfinger.dx,
          internal.tfinger.dy,
          internal.tfinger.pressure,
          internal.tfinger.windowID
        )

      case SDL_EVENT_FINGER_CANCELED => Finger.Canceled(
          internal.deadline,
          internal.tfinger.touchID,
          internal.tfinger.fingerID,
          internal.tfinger.x,
          internal.tfinger.y,
          internal.tfinger.dx,
          internal.tfinger.dy,
          internal.tfinger.pressure,
          internal.tfinger.windowID
        )

      case SDL_EVENT_CLIPBOARD_UPDATE =>
        val mimeTypesBuffer           = internal.clipboard.mime_types
        val mimeTypesSeq: Seq[String] = Seq.tabulate(
          internal.clipboard.num_mime_types
        )(mimeTypesBuffer.getStringUTF8)
        ClipboardUpdate(
          internal.deadline, internal.clipboard.owner, mimeTypesSeq
        )

      case SDL_EVENT_DROP_FILE => Drop.File(
          internal.deadline, internal.drop.windowID, internal.drop.x,
          internal.drop.y, Option(internal.drop.sourceString),
          internal.drop.dataString
        )

      case SDL_EVENT_DROP_TEXT => Drop.Text(
          internal.deadline, internal.drop.windowID, internal.drop.x,
          internal.drop.y, Option(internal.drop.sourceString),
          internal.drop.dataString
        )

      case SDL_EVENT_DROP_BEGIN => Drop.Begin(
          internal.deadline, internal.drop.windowID,
          Option(internal.drop.sourceString)
        )

      case SDL_EVENT_DROP_COMPLETE => Drop.Complete(
          internal.deadline, internal.drop.windowID, internal.drop.x,
          internal.drop.y,
          Option(internal.drop.sourceString)
        )

      case SDL_EVENT_DROP_POSITION => Drop.Position(
          internal.deadline, internal.drop.windowID, internal.drop.x,
          internal.drop.y,
          Option(internal.drop.sourceString)
        )

      case SDL_EVENT_PEN_PROXIMITY_IN => Pen.ProximityIn(
          internal.deadline, internal.pproximity.windowID,
          internal.pproximity.which
        )

      case SDL_EVENT_PEN_PROXIMITY_OUT => Pen.ProximityOut(
          internal.deadline, internal.pproximity.windowID,
          internal.pproximity.which
        )

      case SDL_EVENT_PEN_DOWN => Pen.Down(
          internal.deadline,
          internal.ptouch.windowID,
          internal.ptouch.which,
          internal.ptouch.pen_state.toPenInputSet,
          internal.ptouch.x,
          internal.ptouch.y,
          internal.ptouch.eraser,
          internal.ptouch.down
        )

      case SDL_EVENT_PEN_UP => Pen.Up(
          internal.deadline,
          internal.ptouch.windowID,
          internal.ptouch.which,
          internal.ptouch.pen_state.toPenInputSet,
          internal.ptouch.x,
          internal.ptouch.y,
          internal.ptouch.eraser,
          internal.ptouch.down
        )

      case SDL_EVENT_PEN_BUTTON_DOWN => Pen.ButtonDown(
          internal.deadline, internal.pbutton.windowID, internal.pbutton.which,
          internal.pbutton.pen_state.toPenInputSet
        )

      case SDL_EVENT_PEN_BUTTON_UP => Pen.ButtonUp(
          internal.deadline, internal.pbutton.windowID, internal.pbutton.which,
          internal.pbutton.pen_state.toPenInputSet
        )

      case SDL_EVENT_PEN_MOTION => Pen.Motion(
          internal.deadline, internal.pmotion.windowID, internal.pmotion.which,
          internal.pmotion.pen_state.toPenInputSet, internal.pmotion.x,
          internal.pmotion.y
        )

      case SDL_EVENT_PEN_AXIS => Pen.Axis(
          internal.deadline,
          internal.paxis.windowID,
          internal.paxis.which,
          internal.paxis.pen_state.toPenInputSet,
          internal.paxis.x,
          internal.paxis.y,
          PenAxis.fromInternal(internal.paxis.axis),
          internal.paxis.value
        )

      case SDL_EVENT_CAMERA_DEVICE_ADDED => CameraDevice
          .Added(internal.deadline, internal.cdevice.which)

      case SDL_EVENT_CAMERA_DEVICE_REMOVED => CameraDevice
          .Removed(internal.deadline, internal.cdevice.which)

      case SDL_EVENT_CAMERA_DEVICE_APPROVED => CameraDevice
          .Approved(internal.deadline, internal.cdevice.which)

      case SDL_EVENT_CAMERA_DEVICE_DENIED => CameraDevice
          .Denied(internal.deadline, internal.cdevice.which)

      case SDL_EVENT_RENDER_TARGETS_RESET => Render
          .TargetsReset(internal.deadline, internal.render.windowID)

      case SDL_EVENT_RENDER_DEVICE_RESET => Render
          .DeviceReset(internal.deadline, internal.render.windowID)

      case SDL_EVENT_RENDER_DEVICE_LOST => Render
          .DeviceLost(internal.deadline, internal.render.windowID)
  end fromInternal

  // ALL EVENTS HERE

  /** User-requested quit */
  case class Quit(timestamp: Deadline)        extends Event
  case class Terminating(timestamp: Deadline) extends Event
  case class LowMemory(timestamp: Deadline)   extends Event

  case class WillEnterBackground(timestamp: Deadline) extends Event

  case class DidEnterBackground(timestamp: Deadline) extends Event

  case class WillEnterForeground(timestamp: Deadline) extends Event

  case class DidEnterForeground(timestamp: Deadline) extends Event

  case class LocaleChanged(timestamp: Deadline) extends Event

  case class SystemThemeChanged(timestamp: Deadline) extends Event

  enum Display extends Event:
    def id: DisplayID

    case Orientation(timestamp: Deadline, id: DisplayID)
    case Added(timestamp: Deadline, id: DisplayID)
    case Removed(timestamp: Deadline, id: DisplayID)
    case Moved(timestamp: Deadline, id: DisplayID)

    case DesktopModeChanged(timestamp: Deadline, id: DisplayID)

    case CurrentModeChanged(timestamp: Deadline, id: DisplayID)

    case ContentScaleChanged(timestamp: Deadline, id: DisplayID)

  end Display

  enum Window extends Event:
    def id: WindowID

    /** Window has been shown */
    case Shown(timestamp: Deadline, id: WindowID)

    /** Window has been hidden */
    case Hidden(timestamp: Deadline, id: WindowID)

    /** Window has been exposed and should be redrawn, and can be redrawn
      * directly from event watchers for this event
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

    /** The window with the associated ID is being or has been destroyed. If
      * this message is being handled in an event watcher, the window handle is
      * still valid and can still be used to retrieve any properties associated
      * with the window. Otherwise, the handle has already been destroyed and
      * all resources associated with it are invalid
      */
    case Destroyed(timestamp: Deadline, id: WindowID)

    /** Window HDR properties have changed */
    case HDRStateChanged(timestamp: Deadline, id: WindowID)

  end Window

  enum Key extends Event:
    def windowID: WindowID
    def which: KeyboardID
    def scancode: Scancode
    def key: Keycode
    def mod: Set[Keymod]
    def down: Boolean
    def repeat: Boolean

    case Down(
        timestamp: Deadline,
        windowID: WindowID,
        which: KeyboardID,
        scancode: Scancode,
        key: Keycode,
        mod: Set[Keymod],
        down: Boolean,
        repeat: Boolean)

    case Up(
        timestamp: Deadline,
        windowID: WindowID,
        which: KeyboardID,
        scancode: Scancode,
        key: Keycode,
        mod: Set[Keymod],
        down: Boolean,
        repeat: Boolean)

  end Key

  case class TextEditing(
      timestamp: Deadline,
      windowID: WindowID,
      text: String,
      start: Int,
      length: Int)
      extends Event

  case class TextInput(timestamp: Deadline, windowID: WindowID, text: String)
      extends Event

  case class KeymapChanged(timestamp: Deadline) extends Event

  case class KeyboardAdded(timestamp: Deadline, which: KeyboardID) extends Event

  case class KeyboardRemoved(timestamp: Deadline, which: KeyboardID)
      extends Event

  case class TextEditingCandidates(
      timestamp: Deadline,
      windowID: WindowID,
      candidates: Seq[String],
      selectedCandidate: Option[Int],
      horizontal: Boolean)
      extends Event

  enum Mouse extends Event:
    def which: MouseID

    case ButtonDown(
        timestamp: Deadline,
        windowID: WindowID,
        which: MouseID,
        button: mouse.Button,
        down: Boolean,
        clicks: Byte,
        x: Float,
        y: Float)

    case ButtonUp(
        timestamp: Deadline,
        windowID: WindowID,
        which: MouseID,
        button: mouse.Button,
        down: Boolean,
        clicks: Byte,
        x: Float,
        y: Float)

    case Motion(
        timestamp: Deadline,
        which: MouseID,
        state: Set[mouse.Button],
        x: Float,
        y: Float,
        xrel: Float,
        yrel: Float)

    case Added(timestamp: Deadline, which: MouseID)
    case Removed(timestamp: Deadline, which: MouseID)

  end Mouse

  enum Joystick extends Event:
    def which: JoystickID

    case ButtonDown(
        timestamp: Deadline,
        which: JoystickID,
        button: Byte,
        down: Boolean)

    case ButtonUp(
        timestamp: Deadline,
        which: JoystickID,
        button: Byte,
        down: Boolean)

    case AxisMotion(
        timestamp: Deadline,
        which: JoystickID,
        axis: Byte,
        value: Int)

    case BallMotion(
        timestamp: Deadline,
        which: JoystickID,
        ball: Byte,
        xrel: Int,
        yrel: Int)

    case HatMotion(
        timestamp: Deadline,
        which: JoystickID,
        hat: Byte,
        value: HatPosition)

    case Added(timestamp: Deadline, which: JoystickID)
    case Removed(timestamp: Deadline, which: JoystickID)

    case BatteryUpdated(
        timestamp: Deadline,
        which: JoystickID,
        state: PowerState,
        percent: Int)

    case UpdateComplete(timestamp: Deadline, which: JoystickID)

  end Joystick

  enum Gamepad extends Event:
    def which: JoystickID

    case ButtonDown(
        timestamp: Deadline,
        which: JoystickID,
        button: gamepad.Gamepad.Button,
        down: Boolean)

    case ButtonUp(
        timestamp: Deadline,
        which: JoystickID,
        button: gamepad.Gamepad.Button,
        down: Boolean)

    case AxisMotion(
        timestamp: Deadline,
        which: JoystickID,
        axis: gamepad.Gamepad.Axis,
        value: Int)

    case Added(timestamp: Deadline, which: JoystickID)
    case Removed(timestamp: Deadline, which: JoystickID)
    case Remapped(timestamp: Deadline, which: JoystickID)

    case TouchpadDown(
        timestamp: Deadline,
        which: JoystickID,
        touchpad: Int,
        finger: Int,
        x: Float,
        y: Float,
        pressure: Float)

    case TouchpadMotion(
        timestamp: Deadline,
        which: JoystickID,
        touchpad: Int,
        finger: Int,
        x: Float,
        y: Float,
        pressure: Float)

    case TouchpadUp(
        timestamp: Deadline,
        which: JoystickID,
        touchpad: Int,
        finger: Int,
        x: Float,
        y: Float,
        pressure: Float)

    case SensorUpdate(
        timestamp: Deadline,
        which: JoystickID,
        data: SensorData,
        sensorTimestamp: Long)

    case UpdateComplete(timestamp: Deadline, which: JoystickID)

    case SteamHandleUpdated(timestamp: Deadline, which: JoystickID)

  end Gamepad

  enum Finger extends Event:
    def touchID: touch.TouchID
    def fingerID: touch.FingerID
    def x: Float
    def y: Float
    def dx: Float
    def dy: Float
    def pressure: Float
    def windowID: WindowID

    case Down(
        timestamp: Deadline,
        touchID: touch.TouchID,
        fingerID: touch.FingerID,
        x: Float,
        y: Float,
        dx: Float,
        dy: Float,
        pressure: Float,
        windowID: WindowID)

    case Up(
        timestamp: Deadline,
        touchID: touch.TouchID,
        fingerID: touch.FingerID,
        x: Float,
        y: Float,
        dx: Float,
        dy: Float,
        pressure: Float,
        windowID: WindowID)

    case Motion(
        timestamp: Deadline,
        touchID: touch.TouchID,
        fingerID: touch.FingerID,
        x: Float,
        y: Float,
        dx: Float,
        dy: Float,
        pressure: Float,
        windowID: WindowID)

    case Canceled(
        timestamp: Deadline,
        touchID: touch.TouchID,
        fingerID: touch.FingerID,
        x: Float,
        y: Float,
        dx: Float,
        dy: Float,
        pressure: Float,
        windowID: WindowID)

  end Finger

  /** An event which lets you handle the clipboard
    * @param owner are we owning the clipboard? (internal update)
    * @param mimeTypes current mime types, a mime type is the kind of data
    *   contained in the clipboard. Examples of MIME-types are text/javascript
    *   or image/png.
    */
  case class ClipboardUpdate(
      timestamp: Deadline,
      owner: Boolean,
      mimeTypes: Seq[String])
      extends Event

  enum Drop extends Event:
    def windowID: WindowID
    def source: Option[String]

    case File(
        timestamp: Deadline,
        windowID: WindowID,
        x: Float,
        y: Float,
        source: Option[String],
        filename: String)

    case Text(
        timestamp: Deadline,
        windowID: WindowID,
        x: Float,
        y: Float,
        source: Option[String],
        text: String)

    case Begin(timestamp: Deadline, windowID: WindowID, source: Option[String])

    case Complete(
        timestamp: Deadline,
        windowID: WindowID,
        x: Float,
        y: Float,
        source: Option[String])

    case Position(
        timestamp: Deadline,
        windowID: WindowID,
        x: Float,
        y: Float,
        source: Option[String])

  end Drop

  enum Pen extends Event:
    def windowID: WindowID
    def which: PenID

    case ProximityIn(timestamp: Deadline, windowID: WindowID, which: PenID)

    case ProximityOut(timestamp: Deadline, windowID: WindowID, which: PenID)

    case Down(
        timestamp: Deadline,
        windowID: WindowID,
        which: PenID,
        penState: Set[PenInput],
        x: Float,
        y: Float,
        eraser: Boolean,
        down: Boolean)

    case Up(
        timestamp: Deadline,
        windowID: WindowID,
        which: PenID,
        penState: Set[PenInput],
        x: Float,
        y: Float,
        eraser: Boolean,
        down: Boolean)

    case ButtonDown(
        timestamp: Deadline,
        windowID: WindowID,
        which: PenID,
        penState: Set[PenInput])

    case ButtonUp(
        timestamp: Deadline,
        windowID: WindowID,
        which: PenID,
        penState: Set[PenInput])

    case Motion(
        timestamp: Deadline,
        windowID: WindowID,
        which: PenID,
        penState: Set[PenInput],
        x: Float,
        y: Float)

    case Axis(
        timestamp: Deadline,
        windowID: WindowID,
        which: PenID,
        penState: Set[PenInput],
        x: Float,
        y: Float,
        axis: PenAxis,
        value: Float)

  end Pen

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

  end Render

end Event
