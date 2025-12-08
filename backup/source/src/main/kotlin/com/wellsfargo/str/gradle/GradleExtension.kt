package com.wellsfargo.str.gradle

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

object GradleExtension {
    fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
            add("implementation", dependencyNotation)
    fun DependencyHandler.developmentOnly(dependencyNotation: Any): Dependency? =
        add("developmentOnly", dependencyNotation)
    fun DependencyHandler.runtimeOnly(dependencyNotation: Any): Dependency? =
        add("runtimeOnly", dependencyNotation)
    fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
        add("testImplementation", dependencyNotation)
//    fun DependencyHandler.ksp(dependencyNotation: Any): Dependency? =
//        add("ksp", dependencyNotation)
}

