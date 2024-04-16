plugins {
    id("java") apply true
    id("io.papermc.paperweight.userdev") version "1.5.11" apply true
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

group = "io.github.hiwiscifi.mc.plugins.parkour"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

tasks {
    runServer {
        minecraftVersion("1.20.4")
    }

    compileJava {
        options.encoding = "UTF-8"
    }
}

tasks.test {
    useJUnitPlatform()
}