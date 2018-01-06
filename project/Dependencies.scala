import sbt._

object Dependencies {
  val shapeless   = "com.chuusai"    %% "shapeless"    % "2.3.2"
  val cats        = "org.typelevel"  %% "cats-core"    % "1.0.1"
  val catsLaws    = "org.typelevel"  %% "cats-laws"    % "1.0.1"
  val catsTestkit = "org.typelevel"  %% "cats-testkit" % "1.0.1"
  val discipline  = "org.typelevel"  %% "discipline"   % "0.8"
  val scalaTest   = "org.scalatest"  %% "scalatest"    % "3.0.3"
  val scalaCheck  = "org.scalacheck" %% "scalacheck"   % "1.13.4"
}
