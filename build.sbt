ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "demyst"
  )

  .enablePlugins(
    JavaAppPackaging,
    DockerPlugin
  )
  .settings(
    dockerBaseImage := "adoptopenjdk:11-jre-hotspot"
  )

libraryDependencies ++= Seq(
  "dev.zio"                       %% "zio"                           % "1.0.12",
  "com.softwaremill.sttp.client3" %% "core"                          % "3.9.1",
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % "3.3.6",
  "io.circe"                      %% "circe-core"                    % "0.14.1",
  "io.circe"                      %% "circe-parser"                  % "0.14.1",

  "dev.zio"                       %% "zio-test"                      % "1.0.12"     % "test",
  "dev.zio"                       %% "zio-test-sbt"                  % "1.0.12"     % "test"
)

ThisBuild / javaHome := Some(file("/usr/lib/jvm/java-17-openjdk-amd64"))
