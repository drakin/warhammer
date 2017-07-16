name := "warhammer"

version := "1.0"

lazy val `warhammer` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq("com.typesafe.play" %% "anorm" % "2.5.1")

libraryDependencies ++= Seq(evolutions, jdbc)