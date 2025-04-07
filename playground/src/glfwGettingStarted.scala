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
import scala.util.NotGiven



// this is the corresponding scala code for the tutorial which
// can be found at https://antongerdelan.net/opengl/hellotriangle.html

inline val True = 1

val vertexShader = os.read(os.resource/"shader.vert")
val fragmentShader = os.read(os.resource/"shader.frag")

def panic(msg: String): Nothing =
  errln(s"!!! PANIC: $msg")
  glfwTerminate()
  scala.sys.exit(1)

def errln(msg: String): Unit =
  Console.err.println(Console.RED + msg + Console.RESET)

@main
def main(): Unit =
  glfwSetErrorCallback((error, description) =>
    errln(s"GLFW ERROR: code $error msg: $description.")
  )

  // Start OpenGL context and os window using glfw
  if !glfwInit() then panic("Could not start GLFW3")

  // Request an OpenGL 4.1, core, context from GLFW
  glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
  glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1)
  glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE)
  glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
  glfwWindowHint(GLFW_SAMPLES, 16)

  // Create a window on the operating system,
  // then tie the OpenGL context to it.
  var (winWidth, winHeight) = (800, 600)
  var mon = NULL
  val fullscreen = false
  if fullscreen then
    mon = glfwGetPrimaryMonitor()
    val mode = glfwGetVideoMode(mon)

    glfwWindowHint(GLFW_RED_BITS, mode.redBits())
    glfwWindowHint(GLFW_GREEN_BITS, mode.greenBits())
    glfwWindowHint(GLFW_BLUE_BITS, mode.blueBits())
    glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate())

    winWidth = mode.width()
    winHeight = mode.height()
  end if

  val window: Long = glfwCreateWindow(winWidth, winHeight, "Hello Triangle", mon, NULL) match
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
    // this will hold the pointer to the buffer in the GPU memory
    val vbo = createVbo(
      -0.5,  0.5, 0,
      -0.5, -0.5, 0,
       0.5,  0.5, 0,
       0.5, -0.5, 0,
    )

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
    glBindBuffer(GL_ARRAY_BUFFER, vbo)
    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL)

    // Create some shaders which we WILL need for rendering.
    // The bare minimum is a vertex shader which runs once
    // for every vertex in our mesh.
    val vs = createShader(vertexShader, GL_VERTEX_SHADER)
    val fs = createShader(fragmentShader, GL_FRAGMENT_SHADER)

    val shaderProgram = createProgram(vs, fs)

    val winW = stack.ints(winWidth)
    val winH = stack.ints(winHeight)

    var titleCountdown = 0.1d
    var prev = glfwGetTime()
    while !glfwWindowShouldClose(window) do
      // calculate the fps
      val now = glfwGetTime() // get the current time
      val elapsed = now - prev
      prev = now

      // update window events
      glfwPollEvents()

      titleCountdown -= elapsed
      if titleCountdown <= 0 && elapsed > 0 then
        val fps = 1.0d / elapsed
        print(f"\rFPS: $fps%2.2f")
        titleCountdown = 0.1d
      end if

      if GLFW_PRESS == glfwGetKey(window, GLFW_KEY_ESCAPE) then
        glfwSetWindowShouldClose(window, true)
      end if

      // Check if the window resized
      glfwGetWindowSize(window, winW, winH)
      // Update the viewport (drawing area) to fill the
      // window dimensions.
      glViewport(0, 0, winW.get(0), winH.get(0))

      // wipe the drawing surface clear
      glClearColor(0.6, 0.6, 0.8, 1.0)
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

      val timeLoc = glGetUniformLocation(shaderProgram, "time")
      assert(timeLoc > -1)

      // put the shader program, and the vao,
      // in focus in opengl's state machine.
      glUseProgram(shaderProgram)
      glUniform1f(timeLoc, now.toFloat)
      glBindVertexArray(vao.get(0))
      
      // draw points 0-3 from the currently bound vao
      // with current in-use shader.
      // There are more ways to draw these points than
      // as "triangles". There is also POINTS, LINES,
      // LINE_STRIP, LINE_LOOP, TRIANGLE_STRIP,
      // and TRIANGLE_FAN
      // glDrawArrays(GL_TRIANGLES, 0, 3)
      glDrawArrays(GL_TRIANGLE_STRIP, 0, 4)

      // put the stuff we've been drawing onto the
      // visible area
      glfwSwapBuffers(window);
  )

  // Close OpenGL window, context, and any other GLFW resources.
  glfwTerminate();

end main

def createShader(shader: String, kind: Int): Int =
  val s = glCreateShader(kind)
  glShaderSource(s, shader)
  glCompileShader(s)
  
  Using(stackPush()): stack =>
    val params = stack.ints(-1)
    glGetShaderiv(s, GL_COMPILE_STATUS, params)

    if GL_TRUE != params.get(0) then
      val msg = glGetShaderInfoLog(s)
      errln(s"ERROR: Shader index $s did not compile.\n$msg")

  s

end createShader

def createProgram(shaders: Int*): Int =
  createProgram(true, shaders*)
def createProgram(deleteShaders: Boolean = true, shaders: Int*): Int =
  val program = glCreateProgram()
  for shader <- shaders do
    glAttachShader(program, shader)
  glLinkProgram(program)
  
  Using(stackPush()): stack =>
    val params = stack.ints(-1)
    glGetProgramiv(program, GL_LINK_STATUS, params)

    if GL_TRUE != params.get(0) then
      val msg = glGetProgramInfoLog(program)
      errln(s"ERROR: Could not link shader program GL index $program.\n$msg")

  if deleteShaders then
    shaders foreach glDeleteShader

  program
end createProgram

def createVbo(data: Float*): Int =
  Using(stackPush()): stack =>
    val vbo = stack.mallocInt(1)
    glGenBuffers(vbo) // generate empty buffer
    glBindBuffer(GL_ARRAY_BUFFER, vbo.get(0))
    glBufferData(GL_ARRAY_BUFFER, stack.floats(data*), GL_STATIC_DRAW) // puts stuff into the buffer

    vbo.get(0)
  .get
end createVbo

def createVao(vbos: Int*): Int =
  Using(stackPush()): stack => 
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
    for (vbo, i) <- vbos.zipWithIndex do
      glBindBuffer(GL_ARRAY_BUFFER, vbo)
      glVertexAttribPointer(i, 3, GL_FLOAT, false, 0, NULL)
      glEnableVertexAttribArray(i)

    vao.get(0)
  .get