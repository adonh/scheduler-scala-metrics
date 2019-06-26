lazy val sharedSettings = Seq(
  organization := "com.pagerduty",
  licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
  bintrayOrganization := Some("pagerduty"),
  bintrayRepository := "oss-maven",
  publishMavenStyle := true,
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.8" % Test,
    "org.scalamock" %% "scalamock" % "4.3.0" % Test
  )
)

lazy val api = (project in file("api"))
  .settings(sharedSettings: _*)
  .settings(
    name := "metrics-api"
  )

lazy val gauge = (project in file("gauge"))
  .dependsOn(api)
  .settings(sharedSettings: _*)
  .settings(
    name := "metrics-gauge",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.26"
    )
  )

lazy val dogstatsd = (project in file("dogstatsd"))
  .dependsOn(api)
  .settings(sharedSettings: _*)
  .settings(
    name := "metrics-dogstatsd",
    libraryDependencies ++= Seq(
      "com.datadoghq" % "java-dogstatsd-client" % "2.8",
      "org.mockito" % "mockito-core" % "1.10.19" % "test" // because ScalaMock doesn't work with StatsDClient
    )
  )

skip in publish := true
scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.11.12", "2.12.8", "2.13.0")
