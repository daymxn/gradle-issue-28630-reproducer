package com.example.plugins

import CopyFileContentsTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import java.io.File

abstract class ApiPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val makeChangelogTask = target.tasks.register<CopyFileContentsTask>("makeChangelog") {
            inputFile.set(project.file("changes.txt"))
            outputFile.set(project.file("changelog.md"))
        }

        val changelogFile = makeChangelogTask.flatMap { it.outputFile }
        val releasingVersion = changelogFile.map { parseVersion(it.asFile) }

        target.tasks.register<CopyFileContentsTask>("reproducer") {
            val newApiFile = releasingVersion.map { project.file("$it.api") }

            inputFile.set(project.file("current_api.txt"))
            outputFile.set(project.layout.file(newApiFile))
        }
    }

    private fun parseVersion(apiFile: File): String {
        return apiFile.readLines().first()
    }
}
