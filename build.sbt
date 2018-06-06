name := "fpmortal"

version := "0.1"

scalaVersion := "2.12.6"

scalacOptions in ThisBuild ++= Seq(
  "-language:_",
  "-Ypartial-unification",
  "-Xfatal-warnings",
  "-Ybackend-parallelism 4"
)

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum" % "0.12.0",
  "org.scalaz" %% "scalaz-core" % "7.2.24"
)
