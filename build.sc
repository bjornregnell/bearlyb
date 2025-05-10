package build

import mill.*, scalalib.*, scalafmt.*
import scalalib.api.CompilationResult
import coursier.MavenRepository

object bearlyb extends LwjglModule("3.4.0-SNAPSHOT"):
  def scalaVersion = "3.7.0"

  val deps = Agg(
    ivy"com.lihaoyi::os-lib:0.11.4",
    ivy"com.softwaremill.ox::core:0.5.13",
    ivy"io.github.iltotore::iron:3.0.1",
  )

  def ivyDeps = super.ivyDeps() ++ deps

  def runIvyDeps = super.runIvyDeps()

  def scalacOptions = Seq("-deprecation", "-Wall", "-Yexplicit-nulls", "-Werror")

  object test extends ScalaTests with TestModule.Munit:
    def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.4")

end bearlyb

object playground extends LwjglModule("3.4.0-SNAPSHOT"):
  def scalaVersion = "3.7.0"
  def ivyDeps = Agg(ivy"com.lihaoyi::os-lib:0.11.4") ++ lwjglDeps
  def moduleDeps = Seq(bearlyb)

trait LwjglModule(version: String) extends ScalaModule:

  val lwjglLibs = Agg("lwjgl") ++ Agg(
    "sdl"
  ).map("lwjgl-" + _)

  val lwjglNatives = Agg(
    "linux",
    "linux-arm64",
    "linux-ppc64le",
    "linux-riscv64",
    "macos-arm64",
    "macos",
    "windows-arm64",
    "windows",
    "freebsd"
  )

  def lwjglDep(lib: String, native: Option[String]): Dep =
    val clf = native match
      case Some(native) => s";classifier=natives-$native"
      case None         => ""

    ivy"org.lwjgl:$lib:$version$clf"

  def lwjglDep(lib: String): Dep = lwjglDep(lib, None)

  val lwjglDeps = lwjglLibs.map(lwjglDep)

  val runLwjglDeps =
    for
      dep <- lwjglLibs
      native <- lwjglNatives
    yield lwjglDep(dep, Some(native))

  def forkArgs =
    if isMacOS then Seq("-XstartOnFirstThread") else Seq.empty

  def ivyDeps = lwjglDeps
  def runIvyDeps = runLwjglDeps
  def repositoriesTask = Task.Anon:
    super.repositoriesTask()
      :+ MavenRepository(
        "https://oss.sonatype.org/content/repositories/snapshots/"
      )

end LwjglModule

lazy val isMacOS =
  val osName = sys.props("os.name")
  osName.startsWith("Mac OS X") || osName.startsWith("Darwin")
