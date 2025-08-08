@file:Suppress("SpellCheckingInspection", "unused")

import java.io.FileInputStream
import java.util.*

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}
fun property(propFile: String): Properties =
    (File(propFile) to Properties()).let { pair ->
        if (!pair.first.exists()){
            val artifactoryResolverUsername: String by project
            val artifactoryResolverPassword: String by project
            pair.second.apply {
                setProperty("artifactoryResolverUsername",  artifactoryResolverUsername)
                setProperty("artifactoryResolverPassword", artifactoryResolverPassword)
            }
        }
        else {
            FileInputStream(pair.first).use { fis ->
                return pair.second.also { props ->
                    props.load(fis)
                }
            }
        }
    }
repositories {
    val credentialProps = property("${System.getProperty("user.home")}/.gradle/gradle.properties")
    val gradleProp = property("${rootProject.projectDir}/../gradle.properties")
    val artifactoryResolverUsername: String by credentialProps
    val artifactoryResolverPassword: String by credentialProps
    val mavenRepoUrl: String by gradleProp
    val repoNameList = listOf("maven-wf-virtual", "maven-external-virtual", "maven-1str-virtual", "maven-wf-legacy-virtual")
    fun setMavenRepo(repoHandler: RepositoryHandler) = with(repoHandler) {
        repoNameList.forEach { repoName ->
            maven("${mavenRepoUrl}$repoName") {
                credentials {
                    username = artifactoryResolverUsername
                    password = artifactoryResolverPassword
                }
            }
        }
    }
    setMavenRepo(this)
}
dependencies {
    val props: Properties = property("${rootProject.projectDir}/plugin.version.properties")
    val kotlinVersion: String by props
    val springBootVersion: String by props
    val springDepMgmtVersion: String by props
    val spotlessVersion: String by props
    val jfrogVersion: String by props
    val sonarQubeVersion: String by props
    val openAPIToolsVersion: String by props
    val kotlinKoverVersion: String by props
    val kotlinxBenchmarkVersion: String by props

    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    implementation("io.spring.dependency-management:io.spring.dependency-management.gradle.plugin:$springDepMgmtVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    implementation("org.jetbrains.kotlin.plugin.noarg:org.jetbrains.kotlin.plugin.noarg.gradle.plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlinx.benchmark:org.jetbrains.kotlinx.benchmark.gradle.plugin:$kotlinxBenchmarkVersion")
    implementation("com.jfrog.artifactory:com.jfrog.artifactory.gradle.plugin:$jfrogVersion")
}