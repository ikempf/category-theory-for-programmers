import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "com.ikempf",
        scalaVersion := "2.12.3",
        version := "0.1.0-SNAPSHOT",
        name := "category-theory-for-programmers"
      )),
    libraryDependencies += shapeless,
    libraryDependencies += cats,
    libraryDependencies += scalaTest  % Test,
    libraryDependencies += scalaCheck % Test
  )
