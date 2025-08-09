import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    `java-library`
    id("str.plugin")
}
tasks.jar {
    enabled = true
}
tasks.withType<BootJar> {
    enabled = false
}