plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("myPlugin") {
            id = "com.wellsfargo.str.plugin"
            implementationClass = "org.example.MyPlugin"
        }
    }
}