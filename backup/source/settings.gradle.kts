dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            "${rootProject.projectDir}/../gradle/libs.versions.toml".also { tomlFile ->
                println(">>>>Using version catalog file: $tomlFile for catalog name - libs")
                from(files(tomlFile))
            }
        }
        create("strLibs") {
            "${rootProject.projectDir}/../gradle/libs.versions.toml".also { tomlFile ->
                println(">>>>Using version catalog file: $tomlFile for catalog name - strLibs")
                from(files(tomlFile))
            }
        }
        create("testLibs") {
            "${rootProject.projectDir}/../gradle/test-libs.versions.toml".also { tomlFile ->
                println(">>>>Using version catalog file: $tomlFile for catalog name - testLibs")
                from(files(tomlFile))
            }
        }
    }
}
pluginManagement {
    fun property(propFile: String): java.util.Properties =
        (File(propFile) to java.util.Properties()).let { pair ->
            if (!pair.first.exists()) {
                val artifactoryResolverUsername: String by settings
                val artifactoryResolverPassword: String by settings
                pair.second.apply {
                    setProperty("artifactoryResolverUsername",  artifactoryResolverUsername)
                    setProperty("artifactoryResolverPassword", artifactoryResolverPassword)
                }
            }
            else {
                java.io.FileInputStream(pair.first).use { fis ->
                    return pair.second.also { props ->
                        props.load(fis)
                    }
                }
            }
        }
    val credentialProps = property("${System.getProperty("user.home")}/.gradle/gradle.properties")
    val gradleProp = property("${rootProject.projectDir}/../gradle.properties")
    val artifactoryResolverUsername: String by credentialProps
    val artifactoryResolverPassword: String by credentialProps
    val mavenRepoUrl: String by gradleProp
    val repoNameList = listOf("maven-wf-virtual", "maven-external-virtual", "maven-1str-virtual", "maven-wf-legacy-virtual")
    fun setMavenRepo(repoHandler: RepositoryHandler) = with(repoHandler) {
        repoNameList.forEach { repoName ->
            maven("${mavenRepoUrl}$repoName") {
                credentials {
                    username = artifactoryResolverUsername
                    password = artifactoryResolverPassword
                }
            }
        }
    }
    repositories {
        setMavenRepo(this)
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    fun property(propFile: String): java.util.Properties =
        (File(propFile) to java.util.Properties()).let { pair ->
            if (!pair.first.exists()){
                val artifactoryResolverUsername: String by settings
                val artifactoryResolverPassword: String by settings
                pair.second.apply {
                    setProperty("artifactoryResolverUsername",  artifactoryResolverUsername)
                    setProperty("artifactoryResolverPassword", artifactoryResolverPassword)
                }
            }
            else {
                java.io.FileInputStream(pair.first).use { fis ->
                    return pair.second.also { props ->
                        props.load(fis)
                    }
                }
            }
        }
    val credentialProps = property("${System.getProperty("user.home")}/.gradle/gradle.properties")
    val gradleProp = property("${rootProject.projectDir}/../gradle.properties")
    val artifactoryResolverUsername: String by credentialProps
    val artifactoryResolverPassword: String by credentialProps
    val mavenRepoUrl: String by gradleProp
    val repoNameList = listOf("maven-wf-virtual", "maven-external-virtual", "maven-1str-virtual", "maven-wf-legacy-virtual")
    fun setMavenRepo(repoHandler: RepositoryHandler) = with(repoHandler) {
        repoNameList.forEach { repoName ->
            maven("${mavenRepoUrl}$repoName") {
                credentials {
                    username = artifactoryResolverUsername
                    password = artifactoryResolverPassword
                }
            }
        }
    }
    repositories {
        setMavenRepo(this)
    }
}