import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("str.plugin")
    id("com.jfrog.artifactory")
    application
}

// Create extension object
interface StrAppPluginExtension {
    val message: Property<String>
}

// Add the 'greeting' extension object to project
val extension = project.extensions.create<StrAppPluginExtension>("strapp")

// Set a default value for 'message'
extension.message.convention("Hello from STR App")

// Create a greeting task
abstract class StrAppTask : DefaultTask() {
    @Input
    val message = project.objects.property<String>()

    @TaskAction
    fun greet() {
        println("Message: ${message.get()}")
    }
}
// Register the task and set the convention
tasks.register<StrAppTask>("hello") {
    message.convention(extension.message)
}
tasks.jar {
    enabled = false
}
tasks.withType<BootJar> {
    enabled = true
}