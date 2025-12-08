package com.wellsfargo.str.project

import com.wellsfargo.str.gradle.GradleExtension.implementation
import com.wellsfargo.str.gradle.GradleExtension.testImplementation
import com.wellsfargo.str.project.StrSharedBuildProject.handleProjectDepWithShared
//import gradle.kotlin.dsl.accessors._b50274935e55e8b5c1ce69d3499a506a.testFixturesApi
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object SharedServiceBuildProject {
    const val strSharedServiceProjectName = ":str-service:shared-service"
    const val sharedServiceJarName = "str-shared-service"
    const val strService = "${ProjectGroupName.strBase}.service"
    const val serviceShared = "$strService.shared"
    const val eligibilityService = "$strService.eligibility"
    const val eligibilityServiceJarName = "str-eligibility-service"
    const val cacheService = "$strService.cache"
    const val cacheServiceJarName = "str-cache-service"
    const val dalService = "$strService.dal"
    const val dalServiceJarName = "str-dal-service"

    fun handleProjectDepWithServiceShared(configuration: DependencyHandler) =
        with(configuration) {
            handleProjectDepWithShared(this)
            implementation(project(strSharedServiceProjectName))
//            testFixturesApi(testFixtures(project(strSharedServiceProjectName)))
            testImplementation(testFixtures(project(strSharedServiceProjectName)))
        }
}