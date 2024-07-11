buildscript {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {

        classpath(main.java.Deps.Gradle.kotlinGradlePlugin)
        classpath(main.java.Deps.Gradle.toolsGradlePlugin)

        classpath(main.java.Deps.Gradle.sqlDelight)

        classpath(kotlin("gradle-plugin", version = main.java.Versions.kotlin))
        classpath(kotlin("serialization", version = main.java.Versions.kotlin))

        classpath (main.java.Deps.Gradle.buildKonfig)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}