lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "logging-example",
    version := "0.1",
    scalaVersion := "2.11.7"
  )
