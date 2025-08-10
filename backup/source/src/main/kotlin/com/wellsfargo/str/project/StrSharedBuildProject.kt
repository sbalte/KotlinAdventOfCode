@file:Suppress("ConstPropertyName")

package com.wellsfargo.str.project

import com.wellsfargo.str.gradle.GradleExtension.implementation
import com.wellsfargo.str.gradle.GradleExtension.testImplementation
import com.wellsfargo.str.project.ProjectConstant.EMPTY_STRING
import com.wellsfargo.str.project.ProjectConstant.plainSuffix
import com.wellsfargo.str.project.ProjectGroupName.strBase
import com.wellsfargo.str.project.StrLibraryDependency.handleProjectDependencies
//import gradle.kotlin.dsl.accessors._b50274935e55e8b5c1ce69d3499a506a.testFixturesApi
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.project

object StrSharedBuildProject {
    const val COLON_STRING = ":"
    const val strSharedProjectName = ":str-shared"
    const val strShared = "$strBase.shared"
    const val strSharedJarName = "str-shared"
    fun handleProjectDepWithShared(configuration: DependencyHandler) =
        with(configuration) {
            handleProjectDependencies(this)
            implementation(project(strSharedProjectName))
//            testFixturesApi(testFixtures(project(strSharedProjectName)))
            testImplementation(testFixtures(project(strSharedProjectName)))
        }

    fun handleSharedJarCreation(jar: Jar, jarName: String) {
        with(jar) {
            enabled = true
            archiveBaseName.set(jarName)
            manifest { attributes(manifestMap(project.name, project.version)) }
            archiveFileName.set(archiveFileName.get().replace(plainSuffix, EMPTY_STRING))
        }
    }

    fun handleProjectShared(configuration: DependencyHandler) =
        with(configuration) {
            handleProjectDependencies(this)
//            testFixturesApi(testFixtures(project(strSharedProjectName)))
            testImplementation(testFixtures(project(strSharedProjectName)))
        }
}