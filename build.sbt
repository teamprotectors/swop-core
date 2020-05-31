import Versions.Dependencies

lazy val commonSettings = Seq(
  scalaVersion := "2.12.10",
  version := "0.1.0-SNAPSHOT",
  organization := "co.protectors",
  fork in Test := true,
  name := "swop",
  resolvers ++= Seq(
        Resolver.sonatypeRepo("releases"),
        "confluent" at "https://packages.confluent.io/maven/"
      ),
  libraryDependencies ++= Dependencies.common ++ Dependencies.test,
  dockerRepository := Some("registry.gitlab.com/gsissa/image-registry-repo"),
  daemonUser in Docker := "daemon",
  addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
  addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
)
lazy val ItConfig = config("it") extend (Test)

lazy val testSettings =
  inConfig(ItConfig)(Defaults.testSettings)

lazy val `product-catalog` = project
  .configs(IntegrationTest)
  .settings(
    commonSettings,
    dockerExposedPorts := Seq(8085),
    name += "-product-catalog",
    mainClass in Compile := Option("co.protectors.product.catalog.ports.http.Main"),
    testSettings
  )
  .enablePlugins(JavaAppPackaging)

lazy val users = project
  .configs(IntegrationTest)
  .settings(
    commonSettings,
    dockerExposedPorts := Seq(8081),
    libraryDependencies ++= Dependencies.test,
    mainClass in Compile := Option("co.protectors.user.ports.http.Main"),
    name += "-users",
    testSettings
  )
  .enablePlugins(JavaAppPackaging)

lazy val `swop-cart` = project
  .configs(IntegrationTest)
  .settings(
    commonSettings,
    dockerExposedPorts := Seq(8086),
    libraryDependencies ++= Dependencies.test,
    mainClass in Compile := Option("co.protectors.swop.cart.ports.Main"),
    name += "-cart",
    testSettings
  )
  .enablePlugins(JavaAppPackaging)
