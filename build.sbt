name := "TrakInvest"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "spray repo" at "http://repo.spray.io"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "io.spray" %%  "spray-client" % "1.3.2",
  "io.spray" %%  "spray-json" % "1.3.1",
  "org.mongodb" %% "casbah" % "3.1.1",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.3.9",
  "org.scalatest" % "scalatest_2.11" % "2.2.6"

)
