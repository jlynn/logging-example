lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "logging-example",
    version := "0.1",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "net.logstash.logback" % "logstash-logback-encoder" % "4.5.1"
    )
  )
