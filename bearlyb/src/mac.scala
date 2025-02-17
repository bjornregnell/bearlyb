package bearlyb.mac

import
  java.io,
  io.{OutputStream, InputStream, InputStreamReader},
  java.lang.management.ManagementFactory

import util.boundary, boundary.break

import scala.jdk.CollectionConverters.ListHasAsScala

def restartJVM(args: String*): Unit = boundary:
  val osName = sys.props("os.name")

  if !osName.startsWith("Mac") && !osName.startsWith("Darwin") then break()

  val mxBean = ManagementFactory.getRuntimeMXBean()

  val inputArgs = mxBean.getInputArguments().asScala.toSeq

  if inputArgs.exists(_ == "-XstartOnFirstThread") then break()

  // restart jvm with -XstartOnFirstThread
  println(Console.YELLOW_B + Console.BLACK + "Starting a new JVM with -XstartOnNewThread! Hello mac user :)")
  val classpath = sys.props("java.class.path")
  val mainClass = sys.props("JAVA_MAIN_CLASS_" + mxBean.getPid())
  val jvmPath = os.Path(sys.props("java.home"))/"bin"/"java"
  val jvmArgs = Seq(
    "-XstartOnFirstThread",
  ) ++ inputArgs ++ Seq(
    "-cp",
    classpath,
    mainClass
  ) ++ args

  val subprocess = os.proc(jvmPath, jvmArgs).spawn(stderr = os.Pipe)
  sys.exit(if subprocess.waitFor() then 0 else -1)
end restartJVM