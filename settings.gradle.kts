enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "antlr4-intellij-adaptor"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
        maven("https://central.sonatype.com/repository/maven-snapshots")
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://central.sonatype.com/repository/maven-snapshots")
    }
}