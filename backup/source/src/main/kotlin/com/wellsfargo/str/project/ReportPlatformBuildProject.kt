@file:Suppress("MemberVisibilityCanBePrivate", "ConstPropertyName")

package com.wellsfargo.str.project

import com.wellsfargo.str.gradle.GradleExtension.implementation
import com.wellsfargo.str.gradle.GradleExtension.testImplementation
import com.wellsfargo.str.project.RegHubBuildProject.handleProjectDepWithSharedAdapter
import gradle.kotlin.dsl.accessors._b50274935e55e8b5c1ce69d3499a506a.testFixturesApi
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object ReportPlatformBuildProject {
    const val sharedReportPlatformProjectName = ":report-platform:shared-platform"
    const val reportPlatform = "${ProjectGroupName.strBase}.platform"
    const val platformShared = "$reportPlatform.shared"
    const val sharedPlatformJarName = "str-shared-platform"
    const val iceService = "$reportPlatform.ice"
    const val iceServiceJarName = "str-ice-service"
    const val dtccBasePkgName = "$reportPlatform.dtcc"
    const val amrsDtccService = "$dtccBasePkgName.amrs"
    const val amrsDtccServiceJarName = "str-amrs-dtcc-service"
    const val endurTradeAdapterProjectName = ":reghub:endur-adapter"
    const val iceServiceProjectName = ":report-platform:ice-service"

    private fun handleProjectDepWithPlatformShared(configuration: DependencyHandler) =
        with(configuration) {
            implementation(project(sharedReportPlatformProjectName))
            testFixturesApi(testFixtures(project(sharedReportPlatformProjectName)))
            testImplementation(testFixtures(project(sharedReportPlatformProjectName)))
        }

    fun handleProjectDepWithEndurTradeAdapter(configuration: DependencyHandler) =
        with(configuration) {
            handleProjectDepWithSharedAdapter(this)
            handleProjectDepWithPlatformShared(this)
            implementation(project(endurTradeAdapterProjectName))
            testFixturesApi(testFixtures(project(endurTradeAdapterProjectName)))
            testImplementation(testFixtures(project(endurTradeAdapterProjectName)))
        }
}