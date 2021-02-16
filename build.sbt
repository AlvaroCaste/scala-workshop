
ThisBuild / scalaVersion := "2.13.4"

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
    libraryDependencies ++= Seq(
      "org.scalacheck" %% "scalacheck" % "1.15.2",
      "org.typelevel" %% "cats-core" % "2.3.1"
    )
  )
  .enablePlugins(MdocPlugin)