package build

import mill.*, scalalib.*
import mill.scalalib.api.CompilationResult

object bearlyb extends LwjglModule("3.3.6"):
  def scalaVersion = "3.6.3"

  val deps = Agg(
    ivy"com.lihaoyi::os-lib:0.11.4",
  )
  val runDeps = Agg.empty[Dep] // runtime-only dependencies

  def ivyDeps = lwjglDeps ++ deps

  def runIvyDeps = runLwjglDeps ++ runDeps

  object test extends ScalaTests with TestModule.Munit:
    def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.4")

end bearlyb

object playground extends LwjglModule("3.3.6"):
  def scalaVersion = "3.6.3"
  def ivyDeps = Agg(ivy"com.lihaoyi::os-lib:0.11.4") ++ lwjglDeps

  override def compile = Task:
    val res = resources().map(_.path)
    val shaderFiles =
      res.flatMap(os.walk(_))
        .filter(p => shaderEndings(p.last.split('.').last))
    for shader <- shaderFiles do
      os.call(("glslang", shader))
    super.compile
  end compile

trait LwjglModule(version: String) extends ScalaModule:

  val lwjglLibs = Agg("lwjgl") ++ Agg(
    "assimp", "glfw", "openal", "opengl", "stb",
  ).map("lwjgl-" + _)

  val lwjglNatives = Agg(
    "linux",         "linux-arm64", "linux-ppc64le",
    "linux-riscv64", "macos-arm64", "macos",
    "windows-arm64", "windows",     "freebsd",
  )

  def lwjglDep(lib: String, native: Option[String]): Dep =
    val clf = native match
      case Some(native) => s";classifier=natives-$native" 
      case None => ""

    ivy"org.lwjgl:$lib:$version$clf"

  def lwjglDep(lib: String): Dep = lwjglDep(lib, None)

  val lwjglDeps = lwjglLibs.map(lwjglDep)

  val runLwjglDeps =
    for
      dep <- lwjglLibs
      native <- lwjglNatives
    yield
      lwjglDep(dep, Some(native))

  def forkArgs =
    if isMacOS then Seq("-XstartOnFirstThread") else Seq.empty
    
  def ivyDeps = lwjglDeps
  def runIvyDeps = runLwjglDeps

end LwjglModule

lazy val isMacOS =
  val osName = sys.props("os.name")
  osName.startsWith("Mac OS X") || osName.startsWith("Darwin")

val shaderEndings = Set(
  "conf", "vert", "tesc", "tese", "geom", "frag",
  "comp", "mesh", "task", "rgen", "rint", "rahit",
  "rchit", "rmiss", "rcall", "glsl", "hlsl"
)