package com.wellsfargo.str.project

import com.wellsfargo.str.gradle.GradleExtension.implementation
import com.wellsfargo.str.gradle.GradleExtension.testImplementation
import com.wellsfargo.str.project.StrSharedBuildProject.handleProjectDepWithShared
import gradle.kotlin.dsl.accessors._b50274935e55e8b5c1ce69d3499a506a.testFixturesApi
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object RegHubBuildProject {
    const val regHubSharedProjectName = ":reghub:shared-adapter"
    const val reghub = "${ProjectGroupName.strBase}.reghub"
    const val reghubShared = "$reghub.shared"
    const val endurAdapter = "$reghub.endur"
    const val calypsoAdapter = "$reghub.calypso"
    const val mercuryAdapter = "$reghub.mercury"
    const val sharedAdapterJarName = "str-shared-adapter"
    const val endurAdapterJarName = "str-endur-adapter"
    const val calypsoAdapterJarName = "str-calypso-adapter"
    const val mercuryAdapterJarName = "str-mercury-adapter"

    fun handleProjectDepWithSharedAdapter(configuration: DependencyHandler) =
        with(configuration) {
            handleProjectDepWithShared(this)
            implementation(project(regHubSharedProjectName))
            testFixturesApi(testFixtures(project(regHubSharedProjectName)))
            testImplementation(testFixtures(project(regHubSharedProjectName)))
        }
}