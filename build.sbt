
name := "asist"
organization := "org.clulab"

scalaVersion := "2.12.4"

// uncomment to allow graceful keyboard interrupt
// fork in run := true

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  val javaVersion = sys.props("java.specification.version")
  if (javaVersion != "1.8" && javaVersion.charAt(0) != "8")
   sys.error("Java 1.8 is required for this project. Found " + javaVersion + " instead")
}

//EclipseKeys.withSource := true

resolvers += "Artifactory" at "http://artifactory.cs.arizona.edu:8081/artifactory/sbt-release"

libraryDependencies ++= {
  val procVer = "8.4.2"
  val akkaVersion = "2.6.8"
  val akkaHttpVersion = "10.2.4"
  Seq(
    "org.clulab" %% "processors-main" % procVer,
    "org.clulab" %% "processors-corenlp" % procVer,
    "ai.lum" %% "common" % "0.0.9",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test",
    "com.typesafe" % "config" % "1.3.1",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-slf4j"  % akkaVersion,
    "net.sf.saxon" % "saxon-dom" % "8.7",
    "com.crowdscriber.captions" %% "caption-parser" % "0.1.5",
    "com.google.code.gson" % "gson" % "2.8.5",
    "io.spray" %%  "spray-json" % "1.3.5",
    "org.eclipse.paho" % "org.eclipse.paho.client.mqttv3" % "1.2.5",
  )
}

// Allow the DialogAgent to run in interactive mode
connectInput in run := true

lazy val core: Project = (project in file("."))
  .enablePlugins(BuildInfoPlugin)


lazy val webapp: Project = project
  .enablePlugins(PlayScala)
  .aggregate(core)
  .dependsOn(core)
