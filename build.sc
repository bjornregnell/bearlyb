import mill.*, scalalib.*

object bearlyb extends ScalaModule:
  
  def scalaVersion = "3.6.3"
  
  object test extends ScalaTests with TestModule.Munit:
    def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.4")

end bearlyb
