import dev.karmakrafts.conventions.GitLabCI
import dev.karmakrafts.conventions.authenticatedSonatype
import dev.karmakrafts.conventions.configureJava
import dev.karmakrafts.conventions.defaultDependencyLocking
import dev.karmakrafts.conventions.setRepository
import dev.karmakrafts.conventions.signPublications
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import java.time.Duration

plugins {
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.gradleNexus)
    alias(libs.plugins.karmaConventions)
    alias(libs.plugins.intelliJPlatform)
}

group = "dev.karmakrafts.antlr4"
version = GitLabCI.getDefaultVersion(libs.versions.antlr4Adaptor)
if (GitLabCI.isCI) defaultDependencyLocking()
configureJava(libs.versions.java)

java {
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        create(providers.gradleProperty("platformType"), providers.gradleProperty("platformVersion"))
        bundledPlugins(providers.gradleProperty("platformBundledPlugins").map { it.split(',') })
        plugins(providers.gradleProperty("platformPlugins").map { it.split(',') })
        bundledModules(providers.gradleProperty("platformBundledModules").map { it.split(',') })
        testFramework(TestFrameworkType.Plugin.Java)
    }
    implementation(libs.antlr4.runtime) {
        exclude(group = "com.ibm.icu", module = "icu4j")
    }
    testImplementation(libs.junit)
    testImplementation(libs.openTest4j)
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
    setRepository("git.karmakrafts.dev", "kk/antlr4-intellij-adaptor")
    with(GitLabCI) { karmaKraftsDefaults() }
}

signing {
    signPublications()
}

nexusPublishing {
    authenticatedSonatype()
    connectTimeout = Duration.ofSeconds(30)
    clientTimeout = Duration.ofMinutes(45)
}

intellijPlatform {
    buildSearchableOptions = false
}

tasks {
    buildPlugin {
        enabled = false
    }
    test {
        useJUnitPlatform()
    }
}