package build

import mill.*, scalalib.*

object bearlyb extends LwjglModule("3.3.6"):
  def scalaVersion = "3.6.3"

  val deps = Agg.empty[Dep]
  val runDeps = Agg.empty[Dep] // runtime-only dependencies

  def ivyDeps = lwjglDeps ++ deps

  def runIvyDeps = runLwjglDeps ++ runDeps

  object test extends ScalaTests with TestModule.Munit:
    def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.4")

end bearlyb

object playground extends LwjglModule("3.3.6"):
  def scalaVersion = "3.6.3"



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

  def ivyDeps = lwjglDeps
  def runIvyDeps = runLwjglDeps

end LwjglModule