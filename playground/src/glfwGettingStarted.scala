package glfwGettingStarted

import
  org.lwjgl.*,
  glfw.*,
  system.*,
  opengl.*,
  GLFW.*,
  GL11.*,
  MemoryStack.*,
  MemoryUtil.*
import scala.util.Using

// this is the corresponding scala code for the tutorial which
// can be found at https://www.glfw.org/docs/latest/quick.html

def panic(msg: String): Nothing =
  Console.err.println(s"!!! PANIC: $msg")
  glfwTerminate()
  scala.sys.exit(-1)

def errln(msg: String): Unit = Console.err.println

type Window = Long

def init(): Window =
  GLFWErrorCallback.createPrint(Console.err).set()
  if !glfwInit() then panic("Failed to initialize glfw")

  glfwDefaultWindowHints()
  glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
  glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

  val window: Window =
    glfwCreateWindow(640, 480, "Bearly a title", NULL, NULL) match
      case NULL => panic("Failed to create window")
      case handle => handle
  end window

  glfwMakeContextCurrent(window)
  glfwSwapInterval(1)
  glfwShowWindow(window)
  GL.createCapabilities()

  window
end init

def quit(window: Window): Unit =
  // any calls to destroy resources are really unnecessary
  // because glfwTerminate() releases any remaining resources
  // used by glfw.
  glfwDestroyWindow(window)
  glfwTerminate()
end quit

def gameloop(window: Window): Unit =
  glfwSetKeyCallback(window, (window, key, scancode, action, mods) =>
    if key == GLFW_KEY_ESCAPE && action == GLFW_PRESS then
      glfwSetWindowShouldClose(window, true)
  )

  while !glfwWindowShouldClose(window) do
    Using(stackPush()): stack =>
      val pWidth = stack.mallocInt(1)
      val pHeight = stack.mallocInt(1)

      glfwGetFramebufferSize(window, pWidth, pHeight)
      val width = pWidth.get(0)
      val height = pHeight.get(0)
      val ratio = width / height.toFloat

      glViewport(0, 0, pWidth.get(0), pHeight.get(0))
      glClear(GL_COLOR_BUFFER_BIT)

      glfwSwapBuffers(window)
      glfwPollEvents()
  end while
end gameloop

@main
def main(): Unit =
  val window = init()

  gameloop(window)

  quit(window)