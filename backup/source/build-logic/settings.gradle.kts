rootProject.name = "build-logic"

include("plugin", "task", "shared")

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