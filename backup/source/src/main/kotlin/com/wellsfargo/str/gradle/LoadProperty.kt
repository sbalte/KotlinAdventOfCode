package com.wellsfargo.str.gradle

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import java.io.File
import java.io.FileInputStream
import java.util.*

object LoadProperty {
    fun property(project: Project, propFile: String): Properties {
        project.logger.info(">>>>Loading file: $propFile")
        return property(propFile).also { props ->
            for (key: String in props.stringPropertyNames()) {
                project.logger.info(">>>>key=$key, value=${props.getProperty(key) as String}")
                project.extra[key] = props.getProperty(key) as String
            }
        }
    }

    private fun property(propFile: String): Properties {
        FileInputStream(File(propFile)).use { fis ->
            return Properties().also { props ->
                props.load(fis)
            }
        }
    }
}