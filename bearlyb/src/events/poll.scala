package bearlyb.events

import org.lwjgl.sdl.*
import org.lwjgl.sdl.SDLEvents.*
import org.lwjgl.system.MemoryStack.*
import scala.util.Using
import java.{util => ju}

def poll: Iterator[Event] =
  new Iterator[Event]:
    private var eventBuffer: Option[SDL_Event] = pollEvent

    private def pollEvent: Option[SDL_Event] =
      Using(stackPush()): stack =>
        val event = SDL_Event.malloc(stack)
        if SDL_PollEvent(event) then Some(event)
        else None
      .get

    override def next(): Event =
      (eventBuffer, pollEvent) match
        case (Some(oldEvent), Some(newEvent)) =>
          eventBuffer = Some(newEvent)
          Event.fromInternal(oldEvent)
        case (Some(oldEvent), None) =>
          eventBuffer = None
          Event.fromInternal(oldEvent)
        case (None, _) =>
          throw ju.NoSuchElementException("No more events to handle")

    override def hasNext: Boolean = eventBuffer.nonEmpty
end poll
