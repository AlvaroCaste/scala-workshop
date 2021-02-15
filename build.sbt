
ThisBuild / scalaVersion := "2.13.4"

lazy val exercise1 = project
  .in(file("exercises/exercise1"))

lazy val exercise2 = project
  .in(file("exercises/exercise2"))
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.3" % Test,
      "org.scalacheck" %% "scalacheck" % "1.15.2" % Test,
      "org.scalatestplus" %% "scalacheck-1-15" % "3.2.3.0" % Test
    )
  )

lazy val exercise3 = project
  .in(file("exercises/exercise3"))

lazy val exercise4 = project
  .in(file("exercises/exercise4"))

lazy val exercise5 = project
  .in(file("exercises/exercise5"))
  .dependsOn(exercise3)
  .settings(libraryDependencies ++= dependencies)

lazy val taglessfinal = project
  .in(file("exercises/taglessfinal"))
  .settings(libraryDependencies ++= dependencies)

lazy val docs = project
  .in(file("docs"))
  .settings(
    Compile / scalacOptions ++= Seq(
      "-encoding", "UTF-8", // 2 args
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-Ymacro-annotations"
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full),
    mdocIn := baseDirectory.value / "mdoc",
    mdocOut := baseDirectory.value / "mdoc-out",
    libraryDependencies ++= dependencies
  )
  .enablePlugins(MdocPlugin)

lazy val dependencies = Seq(
  "org.scalacheck" %% "scalacheck" % "1.15.2",
  "org.typelevel" %% "cats-core" % "2.3.1"
)