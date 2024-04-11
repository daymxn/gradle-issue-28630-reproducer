import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class CopyFileContentsTask : DefaultTask() {
    @get:InputFile abstract val inputFile: RegularFileProperty

    @get:OutputFile abstract val outputFile: RegularFileProperty

    @TaskAction
    fun make() {
        outputFile.get().asFile.writeText(inputFile.get().asFile.readText())
    }
}
