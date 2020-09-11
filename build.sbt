
name := "asist"
organization := "org.clulab"

scalaVersion := "2.12.4"

//EclipseKeys.withSource := true

resolvers += "Artifactory" at "http://artifactory.cs.arizona.edu:8081/artifactory/sbt-release"

libraryDependencies ++= {
  val procVer = "7.1.0"

  Seq(
    "org.clulab"    %% "processors-main"          % procVer,
    "org.clulab"    %% "processors-corenlp"       % procVer,
    "org.clulab"    %% "processors-odin"          % procVer,
    "org.clulab"    %% "processors-modelsmain"    % procVer,
    "org.clulab"    %% "processors-modelscorenlp" % procVer,
    "org.clulab"    %% "factuality"               % "1.0.0",
    "org.clulab"    %  "factuality-models"        % "0.2.0",
    "ai.lum"        %% "common"                   % "0.0.9",
    "org.scalatest" %% "scalatest"                % "3.0.4" % "test",
    "com.typesafe"  %  "config"                   % "1.3.1",
    "net.sf.saxon"  % "saxon-dom"                 % "8.7",
    "org.slf4j"     % "slf4j-api"                 % "1.7.10",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
  )
}


lazy val core = project in file(".")

lazy val webapp = project
  .enablePlugins(PlayScala)
  .aggregate(core)
  .dependsOn(core)
