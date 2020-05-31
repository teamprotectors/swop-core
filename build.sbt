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
    name += "-product-catalog",
    mainClass in Compile := Option("co.protectors.product.catalog.ports.http.Main"),
    testSettings
  )

lazy val users = project
  .configs(IntegrationTest)
  .settings(
    commonSettings,
    libraryDependencies ++= Dependencies.test,
    mainClass in Compile := Option("co.protectors.user.ports.http.Main"),
    name += "-users",
    testSettings
  )
