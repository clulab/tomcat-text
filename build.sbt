
name := "asist"
organization := "org.clulab"

scalaVersion := "2.12.4"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  val javaVersion = sys.props("java.specification.version")
  if (javaVersion != "1.8" && javaVersion.charAt(0) != "8")
   sys.error("Java 1.8 is required for this project. Found " + javaVersion + " instead")
}

//EclipseKeys.withSource := true

resolvers += "Artifactory" at "http://artifactory.cs.arizona.edu:8081/artifactory/sbt-release"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.5"
libraryDependencies ++= {
  val procVer = "8.3.7-SNAPSHOT"

  Seq(
    "org.clulab"    %% "processors-main"          % procVer,
    "org.clulab"    %% "processors-corenlp"       % procVer,
    "ai.lum"        %% "common"                   % "0.0.9",
    "org.scalatest" %% "scalatest"                % "3.0.4" % "test",
    "com.typesafe"  %  "config"                   % "1.3.1",
    "net.sf.saxon"  % "saxon-dom"                 % "8.7",
    "org.slf4j"     % "slf4j-api"                 % "1.7.10",
    "com.crowdscriber.captions" %% "caption-parser" % "0.1.5"
  )
}

libraryDependencies ++= Seq(
  "com.google.code.gson" % "gson" % "2.8.5"
)


// trying to get interactive mode running for the DialogAgent
connectInput in run := true


// https://mvnrepository.com/artifact/org.eclipse.paho/org.eclipse.paho.client.mqttv3
libraryDependencies += "org.eclipse.paho" % "org.eclipse.paho.client.mqttv3" % "1.2.5"

lazy val core = project in file(".")

lazy val webapp = project
  .enablePlugins(PlayScala)
  .aggregate(core)
  .dependsOn(core)
