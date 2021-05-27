allprojects {
    repositories {
        mavenCentral()
    }
}

tasks.wrapper {
    description = "Regenerates the Gradle Wrapper"
    gradleVersion = "7.0.2"
}
