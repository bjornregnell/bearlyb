package glfwGettingStarted

import
  org.lwjgl.*,
  glfw.*,
  system.*,
  opengl.*,
  GLFW.*,
  GL11.*, GL15.*, GL20.*, GL30.*, GL41.*,
  GLCapabilities.*,
  MemoryStack.*,
  MemoryUtil.*
import scala.util.Using



// this is the corresponding scala code for the tutorial which
// can be found at https://antongerdelan.net/opengl/hellotriangle.html

inline val True = 1

val vertexShader = os.read(os.resource/"shader.vert")
val fragmentShader = os.read(os.resource/"shader.frag")

def panic(msg: String): Nothing =
  Console.err.println(s"!!! PANIC: $msg")
  glfwTerminate()
  scala.sys.exit(1)

def errln(msg: String): Unit = Console.err.println

type Window = Long

@main
def main(): Unit =
  // Start OpenGL context and os window using glfw
  if !glfwInit() then panic("Could not start GLFW3")

  // Request an OpenGL 4.1, core, context from GLFW
  glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
  glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1)
  glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, True)
  glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
  
  // Create a window on the operating system,
  // then tie the OpenGL context to it.
  val window: Window = glfwCreateWindow(800, 600, "Hello Triangle", NULL, NULL) match
    case NULL => panic("Could not open window with GLFW")
    case handle => handle

  glfwMakeContextCurrent(window) // here we bind the window
  GL.createCapabilities() // extra step because of lwjgl

  // Try to call some OpenGL functions,
  // and print some more version info.
  println(s"Renderer: ${glGetString(GL_RENDERER)}}")
  println(s"OpenGL version supported.\n${glGetString(GL_VERSION)}")

  /* OTHER STUFF GOES HERE */
  Using(stackPush())(stack =>
    val points = stack.floats(
       0.0,  0.5, 0.0,
       0.5, -0.5, 0.0,
      -0.5, -0.5, 0.0
    )
    // this will hold the pointer to the buffer in the GPU memory
    val vbo = stack.mallocInt(1)
    glGenBuffers(vbo) // generate empty buffer
    glBindBuffer(GL_ARRAY_BUFFER, vbo.get(0))
    glBufferData(GL_ARRAY_BUFFER, points, GL_STATIC_DRAW) // puts stuff into the buffer

    // now we want to setup a Vertex Array Object (vao)
    // "WTF IS A VAO????"
    // Well, well, well. When we want to draw a 3D object we
    // apparently have to bind and define the memory layout
    // of every buffer belonging to a object every time we
    // want to render it. In addition to the vertex buffer
    // we might also want buffers with texture coordinates,
    // vertex normals, and more! This becomes a lot of binding
    // and memory-layouting. Not very nice >:(
    // In comes our savior, the VAO! :D
    // It stores all of the buffers that belong to a 3D mesh,
    // so instead of binding every single buffer ONE BY ONE, 
    // we can just bind the VAO and instantly render the mesh
    // with no hassle! Very nice indeed! :)
    val vao = stack.mallocInt(1)
    glGenVertexArrays(vao)
    glBindVertexArray(vao.get(0))
    glEnableVertexAttribArray(0)
    glBindBuffer(GL_ARRAY_BUFFER, vbo.get(0))
    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL)

    // Create some shaders which we WILL need for rendering.
    // The bare minimum is a vertex shader which runs once
    // for every vertex in our mesh.
    val vs = glCreateShader(GL_VERTEX_SHADER)
    glShaderSource(vs, vertexShader)
    glCompileShader(vs)
    val fs = glCreateShader(GL_FRAGMENT_SHADER)
    glShaderSource(fs, fragmentShader)
    glCompileShader(fs)

    val shaderProgram = glCreateProgram()
    glAttachShader(shaderProgram, fs)
    glAttachShader(shaderProgram, vs)
    glLinkProgram(shaderProgram)

    while !glfwWindowShouldClose(window) do
      // update window events
      glfwPollEvents()

      // wipe the drawing surface clear
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

      // put the shader program, and the vao,
      // in focus in opengl's state machine.
      glUseProgram(shaderProgram)
      glBindVertexArray(vao.get(0))
      
      // draw points 0-3 from the currently bound vao
      // with current in-use shader.
      glDrawArrays(GL_TRIANGLES, 0, 3)

      // put the stuff we've been drawing onto the
      // visible area
      glfwSwapBuffers(window);
  )

  // Close OpenGL window, context, and any other GLFW resources.
  glfwTerminate();

end main
