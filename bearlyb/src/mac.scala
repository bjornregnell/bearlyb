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
  
  // get current jvm process pid
  val pid = mxBean.getName().split("@")(0)
  // get environment variable on whether XstartOnFirstThread is enabled
  val env = sys.env.lift("JAVA_STARTED_ON_FIRST_THREAD_" + pid)
  
  // if environment variable is "1" then XstartOnFirstThread is enabled
  env match
    case Some("1") =>
    case _ => break()


  // restart jvm with -XstartOnFirstThread
  println(Console.YELLOW_B + Console.BLACK + "Starting a new JVM with -XstartOnNewThread! Hello mac user :)")
  val classpath = sys.props("java.class.path")
  val mainClass = sys.props("JAVA_MAIN_CLASS_" + pid)
  val jvmPath = os.Path(sys.props("java.home"))/"bin"/"java"
  val jvmArgs = Seq(
    "-XstartOnFirstThread",
  ) ++ mxBean.getInputArguments().asScala ++ Seq(
    "-cp",
    classpath,
    mainClass
  ) ++ args

  val subprocess = os.proc(jvmPath, jvmArgs).spawn(stderr = os.Pipe)
  sys.exit(if subprocess.waitFor() then 0 else -1)
end restartJVM