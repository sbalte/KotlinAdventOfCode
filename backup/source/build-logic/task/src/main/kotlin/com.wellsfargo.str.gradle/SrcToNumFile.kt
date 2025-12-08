package com.wellsfargo.str.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class SrcToNumFile : DefaultTask() {
    @get:Input
    abstract var str: String
    @get:OutputFile
    abstract var outFile: Provider<RegularFile>

    @TaskAction
    fun action() {
        outFile.get().asFile.createNewFile()
        outFile.get().asFile.writeText(str)
    }
}