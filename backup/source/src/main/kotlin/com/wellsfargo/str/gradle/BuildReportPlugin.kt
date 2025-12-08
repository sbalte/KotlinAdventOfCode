package com.wellsfargo.str.gradle

import com.wellsfargo.str.gradle.GradleExtension.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

object BuildReportPlugin {
    const val JACOCO = "0.16.0"
    const val DOKKA = "1.9.20"
    const val KTLINT = "0.49.1"
    const val SPOTLESS = "6.25.0"
    const val DETEKT = "1.23.6"
    const val GRAPH_GENERATOR = "0.8.0"

    private fun handleStrProjectLibraryPlugins(configuration: DependencyHandler) =
        with(configuration) {
            implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
            implementation("org.jetbrains.dokka:dokka-gradle-plugin:$DOKKA")
            implementation("com.pinterest.ktlint:ktlint-core:$KTLINT")
            implementation("com.diffplug.spotless:spotless-plugin-gradle:$SPOTLESS")
            implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$DETEKT")
            implementation("com.vanniktech:gradle-android-junit-jacoco-plugin:$JACOCO")
            implementation("com.vanniktech:gradle-dependency-graph-generator-plugin:$GRAPH_GENERATOR")
        }
}