import Dependencies._

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")

lazy val ctfp = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "com.ikempf",
        scalaVersion := "2.12.3",
        version := "0.1.0-SNAPSHOT",
        name := "category-theory-for-programmers"
      )),
    libraryDependencies ++= Seq(
      shapeless,
      cats,
      catsLaws,
      catsTestkit
    ),
    libraryDependencies ++= Seq(
      scalaTest % Test,
      scalaCheck % Test,
      discipline % Test
    )
  )
