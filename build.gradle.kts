plugins {
    kotlin("jvm") version "2.4.0"
    application
}

group = "d5700"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("d5700.MainKt")
}

tasks.test {
    useJUnitPlatform()
    // The timer.out ROM exercises real-time behavior and the integration
    // tests spin up their own real clocks, so give the suite room to run.
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
