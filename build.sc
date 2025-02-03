import mill.*, scalalib.*

object bearlyb extends ScalaModule:
  def scalaVersion = "3.6.3"
  val lwjglVersion = "3.3.4"

  val deps = Agg()

  val lwjglDeps = Agg("lwjgl") ++ Agg(
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

    ivy"org.lwjgl:$lib:$lwjglVersion$clf"

  def lwjglDep(lib: String): Dep = lwjglDep(lib, None)

  def ivyDeps = lwjglDeps.map(lwjglDep)

  def runIvyDeps =
    for
      dep <- lwjglDeps
      native <- lwjglNatives
    yield
      lwjglDep(dep, Some(native))

  object test extends ScalaTests with TestModule.Munit:
    def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.4")

end bearlyb
