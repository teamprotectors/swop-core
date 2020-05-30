import sbt._

object Versions {
  lazy val cats        = "2.1.1"
  lazy val catsEffects = "2.1.2"
  lazy val catsMTL     = "0.7.1"
  lazy val circeConfig = "0.7.0"
  lazy val circe       = "0.12.1"
  lazy val mouse       = "0.25"
  lazy val scalaTest   = "3.1.1"
  lazy val scalaCheck  = "1.14.3"
  lazy val doobie      = "0.8.8"
  lazy val henkan      = "0.6.4"

  object Dependencies {
    lazy val common: Seq[ModuleID] = Seq(
      "org.typelevel"  %% "cats-core"       % Versions.cats withSources () withJavadoc (),
      "org.typelevel"  %% "cats-effect"     % Versions.catsEffects withSources () withJavadoc (),
      "org.typelevel"  %% "mouse"           % Versions.mouse withSources () withJavadoc (),
      "org.typelevel"  %% "cats-mtl-core"   % Versions.catsMTL,
      "org.tpolecat"   %% "doobie-core"     % Versions.doobie,
      "org.tpolecat"   %% "doobie-h2"       % Versions.doobie,
      "org.tpolecat"   %% "doobie-hikari"   % Versions.doobie,
      "org.tpolecat"   %% "doobie-postgres" % Versions.doobie,
      "io.circe"       %% "circe-config"    % Versions.circeConfig,
      "io.circe"       %% "circe-core"      % Versions.circe,
      "io.circe"       %% "circe-parser"    % Versions.circe,
      "io.circe"       %% "circe-generic"   % Versions.circe,
      "com.kailuowang" %% "henkan-convert"  % Versions.henkan,
      "com.kailuowang" %% "henkan-optional" % Versions.henkan
    )

    lazy val test: Seq[ModuleID] = Seq(
      "org.scalatest"  %% "scalatest"  % Versions.scalaTest,
      "org.scalacheck" %% "scalacheck" % Versions.scalaCheck % s"it,$Test"
    )

  }

}
