package bearlyb

import
  org.lwjgl.*,
  org.lwjgl.glfw.*,
  org.lwjgl.opengl.*,
  org.lwjgl.system.*,
  org.lwjgl.glfw.GLFW.*,
  org.lwjgl.glfw.Callbacks.*,
  org.lwjgl.opengl.GL11.*,
  org.lwjgl.system.MemoryStack.*,
  org.lwjgl.system.MemoryUtil.*

import scala.util.Using
import java.io.File

def init(): Unit =
  GLFWErrorCallback.createPrint(System.err).set()

  if !glfwInit() then
    throw new IllegalStateException("Unable to initialize GLFW")

  glfwDefaultWindowHints()
  glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
  glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

  glfwCreateWindow(300, 300, "Hello World!", NULL, NULL) match
    case NULL =>
      throw new RuntimeException("Failed to create the GLFW window")
    case handle => window = Some(handle)

  glfwSetKeyCallback(window.get, (win, key, scancode, action, mods) =>
    if key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE then
      glfwSetWindowShouldClose(window.get, true)
  )

  Using(stackPush()): stack =>
    val pWidth = stack.mallocInt(1)
    val pHeight = stack.mallocInt(1)
  
    glfwGetWindowSize(window.get, pWidth, pHeight)

    val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())

    glfwSetWindowPos(
      window.get,
      (vidmode.width() - pWidth.get(0)) / 2,
      (vidmode.height() - pHeight.get(0)) / 2,
    )
  
  glfwMakeContextCurrent(window.get)
  glfwSwapInterval(1)

  glfwShowWindow(window.get)

end init

def loop(): Unit =

  GL.createCapabilities()

  glClearColor(1f, 0f, 0f, 0f)

  while !glfwWindowShouldClose(window.get) do
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

    glfwSwapBuffers(window.get)

    glfwPollEvents()

var window: Option[Long] = None

@main def main(): Unit =
  println(s"Hello LWJGL ${Version.getVersion()}!")

  init()
  loop()

  glfwFreeCallbacks(window.get)
  glfwDestroyWindow(window.get)

  glfwTerminate()
  glfwSetErrorCallback(null).free()
end main