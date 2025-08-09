import com.wellsfargo.str.project.BuildUtil
import com.wellsfargo.str.project.ProjectJava
import com.wellsfargo.str.project.ProjectJava.jvmTargetProp
import com.wellsfargo.str.project.ProjectJava.sourceCompatibilityVersion
import com.wellsfargo.str.project.ProjectJava.targetCompatibilityVersion
import com.wellsfargo.str.project.ProjectKotlin.compilerArgs
import com.wellsfargo.str.project.SpringVersion
import com.wellsfargo.str.project.StrLibraryDependency.handleProjectDependencies
import com.wellsfargo.str.project.StrSharedBuildProject
import org.gradle.kotlin.dsl.provideDelegate
import java.util.Properties
import kotlin.collections.getValue

plugins {
    `java-library`
    `java-gradle-plugin`
    java
    idea
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.noarg")
    id("java-test-fixtures")
    id("org.jetbrains.kotlinx.benchmark")
    `maven-publish`
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(ProjectJava.javaVersionNumber)
    }
    sourceCompatibility = sourceCompatibilityVersion
    targetCompatibility = targetCompatibilityVersion
}
ext {
    set("jackson-bom.version", SpringVersion.jacksonBomVersion)
//    set("spring-framework.version", SpringVersion.springFrameworkVersion)
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(ProjectJava.javaVersionNumber)
}
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll(compilerArgs)
        jvmTarget = jvmTargetProp
    }
//    sourceSets {
//        dependencies {
//            implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
//        }
//    }
}
dependencies {
    handleProjectDependencies(this)
}
allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}
val sourcesJar by tasks.register<Jar>("sourceJar") {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}
val javadocJar by tasks.register<Jar>("javadocJar") {
    val javaDocStr = "javadoc"
    dependsOn(javaDocStr)
    archiveClassifier.set(javaDocStr)
    from(tasks[javaDocStr])
}
tasks.withType<Test> {
    systemProperties["org.gradle.caching"]=true
    systemProperties["org.gradle.parallel"]=true
    systemProperties["org.gradle.debug"]=false
    systemProperties["org.gradle.configuration-cache"]=true
}
configurations.all {
    val props: Properties = BuildUtil.property("${rootProject.projectDir}/buildSrc/plugin.version.properties")
    val kotlinVersion: String by props
    val depVersionMap = mapOf(
        "org.apache.struts:struts-core" to "1.3.10",
        "org.jetbrains.kotlin:kotlin-stdlib" to kotlinVersion,
        "org.jetbrains.kotlin:kotlin-reflect" to kotlinVersion,
    )
    val excludeJarList = listOf(
        "com.vaadin.external.google:android-json" to "0.0.20131108.vaadin1",
    )
    resolutionStrategy.cacheChangingModulesFor(30, TimeUnit.DAYS)
    resolutionStrategy.eachDependency {
        "${requested.group}:${requested.name}".let { depName ->
            if (depVersionMap.containsKey(depName)) {
                useVersion(depVersionMap[depName]!!)
            }
        }
    }
    excludeJarList.forEach { exclude(group = it.first, module = it.second) }
}