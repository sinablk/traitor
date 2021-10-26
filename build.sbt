name := """traitor"""
organization := "com.traitor"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.commonmark" % "commonmark" % "0.18.0"
libraryDependencies += "com.lihaoyi" %% "scalatags" % "0.9.4"
libraryDependencies += "joda-time" % "joda-time" % "2.10.12"

// Adds additional packages into Twirl
TwirlKeys.templateImports += "models._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.traitor.binders._"
