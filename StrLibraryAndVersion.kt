@file:Suppress("unused", "MemberVisibilityCanBePrivate", "ConstPropertyName", "SpellCheckingInspection")

package com.wellsfargo.str.project

import com.wellsfargo.str.project.ProjectApache.apachePOIVersion
import com.wellsfargo.str.project.ProjectApache.apacheVelocityToolVersion
import com.wellsfargo.str.project.ProjectApache.apacheVelocityVersion
import com.wellsfargo.str.project.ProjectApache.bsdFreemakerTemplateEngineVersion
import com.wellsfargo.str.project.ProjectApache.commonsLang3Version
import com.wellsfargo.str.project.ProjectApache.hubbardsXPathVersion
import com.wellsfargo.str.project.ProjectApache.log4jKotlinVersion
import com.wellsfargo.str.project.ProjectApache.thymeleafTemplateEngineVersion
import com.wellsfargo.str.project.ProjectArrow.arrowKotlinVersion
import com.wellsfargo.str.project.ProjectKotlin.klibsFlowExtVersion
import com.wellsfargo.str.project.ProjectKotlin.kosonJsonBuilderVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinDataframeVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinDatetimeVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinHtmlJvmVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinIOCoreVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinImmutableCollectionVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinKMathVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinMultiKVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinReduxVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinXmlBuilderVersion
import com.wellsfargo.str.project.ProjectKotlin.kotlinxBenchmarkVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.googleGsonVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.googleGuavaVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.http4kBomVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.jasyptVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.jaywayJsonPathVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.openJdkJmhVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.reactiveMutinyVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.solaceClientVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.solaceJmsJakartaVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.solaceVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.swaggerParserVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.testApprovalVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.vavrVersion
import com.wellsfargo.str.project.ProjectOtherLibrary.xmlUnitCoreVersion
import com.wellsfargo.str.project.ProjectRuntimeLibrary.mssqlR2DBCVersion
import com.wellsfargo.str.project.ProjectTestLibrary.arrowCoreTestVersion
import com.wellsfargo.str.project.ProjectTestLibrary.assertJCoreVersion
import com.wellsfargo.str.project.ProjectTestLibrary.koTestArrowVersion
import com.wellsfargo.str.project.ProjectTestLibrary.koTestExtSpringVersion
import com.wellsfargo.str.project.ProjectTestLibrary.koTestKOTestExtSpringVersion
import com.wellsfargo.str.project.ProjectTestLibrary.koTestVersion
import com.wellsfargo.str.project.ProjectTestLibrary.kotlinCoroutineTestVersion
import com.wellsfargo.str.project.ProjectTestLibrary.mockkJvmVersion
import com.wellsfargo.str.project.ProjectTestLibrary.striKtVersion
import com.wellsfargo.str.project.ProjectTestLibrary.testContainerJupiter
import com.wellsfargo.str.project.ProjectTestLibrary.testHttp4k4TestVersion
import com.wellsfargo.str.project.ProjectTestLibrary.testMockitoInlineVersion
import com.wellsfargo.str.project.ProjectTestLibrary.testOkHttpVersion
import com.wellsfargo.str.project.SpringVersion.jacksonVersion
import com.wellsfargo.str.project.SpringVersion.jaxbAPIVersion
import com.wellsfargo.str.project.SpringVersion.jaxbVersion
import com.wellsfargo.str.project.SpringVersion.springModulithVersion
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.*

const val projectReleaseVersion = "1.0.0-SNAPSHOT"
fun manifestMap(projectName: String, projectVersion: Any) : Map<String, Any> {
    var projectVersionInput: Any? = System.getProperty("version") // check if version is passed in arguments
    if (Objects.isNull(projectVersionInput) || Objects.equals(projectVersionInput, "unspecified")) {
        projectVersionInput = projectVersion
    }
    return mapOf(
        "Implementation-Title" to projectName,
        "Implementation-Version" to projectVersionInput.toString()
    )
}
fun String.lowerFirstChar() = replaceFirstChar(Char::titlecase)
object ProjectJava {
    private val javaVersionPair = JavaVersion.VERSION_17 to 17
    val jvmTargetProp by lazy { JvmTarget.JVM_17 }
    val javaVersion by lazy { javaVersionPair.first }
    val sourceCompatibilityVersion by lazy { javaVersionPair.first }
    val targetCompatibilityVersion by lazy { javaVersionPair.first }
    val javaVersionNumber: Int by lazy { javaVersionPair.second }
    val javaVersionStr by lazy { javaVersionPair.second.toString() }
}

object ProjectOrchestraVersion {
    const val orchestraFrameworkVersion = "3.5.1"
    const val variableTokenVersion = "3.0.20"
}

object ProjectCICDVersion {
    const val sonarQubeVersion = "4.4.1.3373"
    const val mooWorkVersion = "1.3.1"
}

object SpringVersion {
    const val nativeBuildTool = "0.9.20"
    const val jacksonVersion = "2.17.2"
    const val jacksonBomVersion = "2.17.2"
    const val jaxbVersion = "4.0.5"
    const val jaxbAPIVersion = "2.4.0-b180830.0359"
    const val springModulithVersion = "1.2.0"
}

object ProjectKotlin {
    const val kotlinImmutableCollectionVersion = "0.3.5"
    const val kotlinHtmlJvmVersion = "0.6.10"
    const val kotlinXmlBuilderVersion = "1.9.1"
    const val kosonJsonBuilderVersion = "1.2.8"
    const val kotlinDataframeVersion = "0.15.0"
    const val kotlinKMathVersion = "0.3.1"
    const val kotlinMultiKVersion = "0.2.3"
    const val kotlinDatetimeVersion = "0.6.1"
    const val kotlinIOCoreVersion = "0.3.5"
    const val kotlinReduxVersion = "0.5.5"
    const val kotlinxBenchmarkVersion = "0.4.13"
    const val klibsFlowExtVersion = "1.0.0"
    val compilerArgs = listOf(
        "-Xjsr305=strict",
        "-Xconsistent-data-class-copy-visibility",
        //Kotlin 2.2
        "-Xcontext-parameters",
        "-Xcontext-sensitive-resolution",
        "-Xannotation-target-all",
        "-Xannotation-default-target=param-property",
        "-Xnested-type-aliases",
    )
}

object ProjectArrow {
    const val arrowKotlinVersion = "1.2.4" //"2.1.0"
}

object ProjectApache {
    const val log4jKotlinVersion = "1.5.0"
    const val commonsLang3Version = "3.12.0"
    const val apacheVelocityVersion = "1.7"
    const val bsdFreemakerTemplateEngineVersion = "2.3.32"
    const val thymeleafTemplateEngineVersion = "3.1.2.RELEASE"
    const val apacheVelocityToolVersion = "2.0"
    const val apachePOIVersion = "5.2.5"
    const val hubbardsXPathVersion = "0.0.6"
}

object ProjectOtherLibrary {
    const val vavrVersion = "0.10.4"
    const val jasyptVersion = "1.9.3"
    const val redissonVersion = "3.22.0"
    const val reactiveMutinyVersion = "2.6.0"
    const val googleGuavaVersion = "33.4.8-jre"
    const val googleGsonVersion = "2.11.0"
    const val xmlUnitCoreVersion = "2.9.1"
    const val solaceVersion = "10.22.0"
    const val solaceClientVersion = "1.4.0"
    const val http4kBomVersion = "5.47.0.0"
    const val swaggerParserVersion = "2.1.22"
    const val solaceJmsJakartaVersion = "10.23.0"
    const val coherenceVersion = "14.1.1-0-3"
    const val csfClientVersion = "2.0.0.4"
    const val strCacheJarVersion = "24.04.0.27"
    const val jaywayJsonPathVersion = "2.9.0"
    const val testApprovalVersion = "24.9.0"
    const val openJdkJmhVersion = "1.37"
}

object ProjectRuntimeLibrary {
    const val mssqlR2DBCVersion = "1.0.2.RELEASE"
}

object ProjectTestLibrary {
    const val koTestVersion = "5.9.1"
    const val koTestKOTestExtSpringVersion = "4.4.3"
    const val koTestArrowVersion = "1.4.0"
    const val koTestExtSpringVersion = "1.1.3"
    const val mockkJvmVersion = "1.13.8"
    const val arrowCoreTestVersion = "1.1.2"
    const val assertJCoreVersion = "3.25.3"
    const val testContainerJupiter = "1.19.7"
    const val testMockitoInlineVersion = "5.2.0"
    const val testOkHttpVersion = "4.12.0"
    const val striKtVersion = "0.35.1"
    const val testHttp4k4TestVersion = "5.29.0.0"
    const val kotlinCoroutineTestVersion = "1.9.0-RC.2"
}

enum class ProjectTarCreaation(val tarName: String) {
    NextGenServiceRunScriptTar("ngScriptDistTar"),
    NextGenCacheServiceTar("ngCacheServiceDistTar"),
    NextGenIceServiceTar("ngICEServiceDistTar");
    fun jFrogArtifactoryPluginName() = "com.jfrog.artifactory"
    operator fun invoke() = tarName
    fun gradleTaskName() = this.name.lowerFirstChar()
}

object SpringDep {
    //spring
    val springBootActuator by lazy { "org.springframework.boot:spring-boot-starter-actuator" }
    val springBootDataRedis by lazy { "org.springframework.boot:spring-boot-starter-data-redis" }
    val springBootDataRedisReactive by lazy { "org.springframework.boot:spring-boot-starter-data-redis-reactive" }
    val springBootWebflux by lazy { "org.springframework.boot:spring-boot-starter-webflux" }
    val springBootR2DBC by lazy { "org.springframework.boot:spring-boot-starter-data-r2dbc" }
    val springBootIntegrationR2DBC by lazy { "org.springframework.integration:spring-integration-r2dbc" }
    val springBootIntegration by lazy { "org.springframework.boot:spring-boot-starter-integration" }
    val springBootLog4j2 by lazy { "org.springframework.boot:spring-boot-starter-log4j2" }
    val springBootStarterSecurity by lazy {"org.springframework.boot:spring-boot-starter-security"}
    val springBootIntegrationWebflux by lazy { "org.springframework.integration:spring-integration-webflux" }
    val reactorKotlinExtension by lazy { "io.projectreactor.kotlin:reactor-kotlin-extensions" }
    val springModulith by lazy { "org.springframework.modulith:spring-modulith-starter-core:$springModulithVersion" }
    val springBootIntegrationJms by lazy { "org.springframework.integration:spring-integration-jms" }
    val springBootIntegrationFile by lazy { "org.springframework.integration:spring-integration-file" }
    val springBootMail by lazy { "org.springframework.boot:spring-boot-starter-mail" }

    //jackson
    val jacksonKotlinModule by lazy { "com.fasterxml.jackson.module:jackson-module-kotlin" }
    val jacksonDataFormatCsv by lazy { "com.fasterxml.jackson.dataformat:jackson-dataformat-csv:$jacksonVersion" }
    val jacksonDataFormatXml by lazy { "com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion" }
    val jacksonDataFormatYaml by lazy { "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion" }
    val jaxbBindXjc by lazy { "com.sun.xml.bind:jaxb-xjc:$jaxbVersion" }
    val jaxbBindImpl by lazy { "com.sun.xml.bind:jaxb-impl:$jaxbVersion" }
    val jaxbApi by lazy { "javax.xml.bind:jaxb-api:$jaxbAPIVersion" }
    val jacksonAnnotation by lazy { "com.fasterxml.jackson.module:jackson-module-jaxb-annotations:$jacksonVersion" }

    //dev tools
    val springBootDevTool by lazy { "org.springframework.boot:spring-boot-devtools" }

    //docker
    val springBootDockerCompose by lazy { "org.springframework.boot:spring-boot-docker-compose" }
}

object KotlinDep {
    //kotlin
    val kotlinReflect by lazy { "org.jetbrains.kotlin:kotlin-reflect" }
    val kotlinCoroutineReactor by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-reactor" }
    val kotlinImmutableCollection by lazy { "org.jetbrains.kotlinx:kotlinx-collections-immutable:$kotlinImmutableCollectionVersion" }
    val kotlinHtmlJvm by lazy { "org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinHtmlJvmVersion" }
    val kotlinXmlBuilder by lazy { "org.redundent:kotlin-xml-builder:$kotlinXmlBuilderVersion" }
    val kosonJsonBuilder by lazy { "com.lectra:koson:$kosonJsonBuilderVersion" }
    val kotlinDataframe by lazy { "org.jetbrains.kotlinx:dataframe:$kotlinDataframeVersion" }
    val kotlinKMath by lazy { "space.kscience:kmath-core-jvm:$kotlinKMathVersion" }
    val kotlinMultiKCore by lazy { "org.jetbrains.kotlinx:multik-core:$kotlinMultiKVersion" }
    val kotlinMultiKDefault by lazy { "org.jetbrains.kotlinx:multik-default:$kotlinMultiKVersion" }
    val kotlinDatetime by lazy { "org.jetbrains.kotlinx:kotlinx-datetime:$kotlinDatetimeVersion" }
    val kotlinIOCore by lazy { "org.jetbrains.kotlinx:kotlinx-io-core:$kotlinIOCoreVersion" }
    val kotlinRedux by lazy { "org.reduxkotlin:redux-kotlin-threadsafe:$kotlinReduxVersion" }
    val kotlinxBenchmark by lazy { "org.jetbrains.kotlinx:kotlinx-benchmark-runtime:$kotlinxBenchmarkVersion" }
    val klibsFlowExt by lazy { "io.github.hoc081098:FlowExt:$klibsFlowExtVersion" }

    //arrow
    val arrowStack by lazy { "io.arrow-kt:arrow-stack:$arrowKotlinVersion" }
    val arrowCore by lazy { "io.arrow-kt:arrow-core" }
    val arrowFxCoroutine by lazy { "io.arrow-kt:arrow-fx-coroutines" }
    val arrowOptics by lazy { "io.arrow-kt:arrow-optics:$arrowKotlinVersion" }
//    val arrowKSP by lazy { "io.arrow-kt:arrow-optics-ksp-plugin:$arrowKotlinVersion" }
    val arrowSTM by lazy { "io.arrow-kt:arrow-fx-stm:$arrowKotlinVersion" }
    val arrowEval by lazy { "io.arrow-kt:arrow-eval:$arrowKotlinVersion" }
    val arrowAutoClose by lazy { "io.arrow-kt:arrow-autoclose:$arrowKotlinVersion" }
}

object OtherLibraryDep {
    val radisson by lazy { "org.redisson:redisson:${ProjectOtherLibrary.redissonVersion}" }

    //apache
    val apacheCommonsLang3 by lazy { "org.apache.commons:commons-lang3:$commonsLang3Version" }
    val apacheLog4jKotlin by lazy { "org.apache.logging.log4j:log4j-api-kotlin:$log4jKotlinVersion" }
    val apacheVelocity by lazy { "org.apache.velocity:velocity:$apacheVelocityVersion" }
    val apacheVelocityTool by lazy { "org.apache.velocity:velocity-tools:$apacheVelocityToolVersion" }
    val bsdFreemakerTemplateEngine by lazy { "org.freemarker:freemarker:$bsdFreemakerTemplateEngineVersion" }
    val thymeleafTemplateEngine by lazy { "org.thymeleaf:thymeleaf:$thymeleafTemplateEngineVersion" }
    val thymeleafSpringTemplateEngine by lazy { "org.thymeleaf:thymeleaf-spring6:$thymeleafTemplateEngineVersion" }
    val apachePoi by lazy { "org.apache.poi:poi:$apachePOIVersion" }
    val apachePoiOoxml by lazy { "org.apache.poi:poi-ooxml:$apachePOIVersion" }
    val hubbardsXPath by lazy { "com.github.hubbards:xpath-kotlin:$hubbardsXPathVersion" }

    //mutiny
    val reactiveMutiny by lazy { "io.smallrye.reactive:mutiny:$reactiveMutinyVersion" }
    val reactiveMutinyReactor by lazy { "io.smallrye.reactive:mutiny-reactor:$reactiveMutinyVersion" }
    val reactiveMutinyKotlin by lazy { "io.smallrye.reactive:mutiny-kotlin:$reactiveMutinyVersion" }

    //google
    val googleGuava by lazy { "com.google.guava:guava:$googleGuavaVersion" }
    val googleGson by lazy { "com.google.code.gson:gson:$googleGsonVersion" }

    //solace
    val solaceJcsmp by lazy { "com.solacesystems:sol-jcsmp:$solaceVersion" }
    val solaceJms by lazy { "com.solacesystems:sol-jms:$solaceVersion" }
    val solaceMsgClient by lazy { "com.solace:solace-messaging-client:$solaceClientVersion" }
    val solaceJmsJakarta by lazy { "com.solacesystems:sol-jms-jakarta:$solaceJmsJakartaVersion" }
    //xml unit
    val xmlUnitCore by lazy { "org.xmlunit:xmlunit-core:$xmlUnitCoreVersion" }
    //vavr
    val vavr by lazy { "io.vavr:vavr:$vavrVersion" }
    //swagger
    val swaggerParser by lazy { "io.swagger.parser.v3:swagger-parser:$swaggerParserVersion" }
    //others
    val jasypt by lazy { "org.jasypt:jasypt:$jasyptVersion" }
    val jaywayJsonPath by lazy { "com.jayway.jsonpath:json-path:$jaywayJsonPathVersion" }
    val openJdkJmhCore by lazy { "org.openjdk.jmh:jmh-core:$openJdkJmhVersion" }
    val openJdkJmhAnnProcess by lazy { "org.openjdk.jmh:jmh-generator-annprocess:$openJdkJmhVersion" }
    val http4kBom by lazy { "org.http4k:http4k-bom:$http4kBomVersion" }
    val http4kCore by lazy { "org.http4k:http4k-core" }
    val http4kMultipart by lazy { "org.http4k:http4k-multipart" }
    val http4kNetty by lazy { "org.http4k:http4k-server-netty" }
    val http4kJettyServer by lazy { "org.http4k:http4k-server-jetty" }
    val http4kWebSocketServer by lazy { "org.http4k:http4k-server-websocket" }
    val http4kJackson by lazy { "org.http4k:http4k-format-jackson" }
    val http4kXml by lazy { "org.http4k:http4k-format-xml" }
    val http4kJacksonXml by lazy { "org.http4k:http4k-format-jackson-xml" }
    val http4kClientOkHttp by lazy { "org.http4k:http4k-client-okhttp" }
    val http4kWebSocketClient by lazy { "org.http4k:http4k-client-websocket" }
    val http4kClientApache by lazy { "org.http4k:http4k-client-apache-async" }
    val http4kJettyClient by lazy { "org.http4k:http4k-client-jetty" }
    val http4kNettyClient by lazy { "org.http4k:http4k-client-netty" }
    val http4kFreeMarkerTemplate by lazy { "org.http4k:http4k-template-freemarker" }
    val http4kThymeleafTemplate by lazy { "org.http4k:http4k-template-thymeleaf" }
    val http4kKotlinSerialization by lazy {"org.http4k:http4k-format-kotlinx-serialization"}
}

object RuntimeDep {
    val mssqlR2DBC by lazy { "io.r2dbc:r2dbc-mssql:$mssqlR2DBCVersion" }
    val mssqlJdbc by lazy { "com.microsoft.sqlserver:mssql-jdbc" }
}

object TestDep {
    const val kotlinTestVersion = "2.2.0" //TODO: later use $kotlinVersion, as currently there is version conflict
    val testSpringBootStarter by lazy { "org.springframework.boot:spring-boot-starter-test" }
    val testSpringSecurity by lazy { "org.springframework.security:spring-security-test" }
    val testSpringSecurityReactive by lazy { "org.springframework.security:spring-security-reactive" }
    val testSpringBootTestContainer by lazy { "org.springframework.boot:spring-boot-testcontainers" }
    val testSpringBootReactor by lazy { "io.projectreactor:reactor-test" }
    val testJunitJupiter by lazy { "org.testcontainers:junit-jupiter:$testContainerJupiter" }
    val testAssetJCore by lazy { "org.assertj:assertj-core:$assertJCoreVersion" }
    val testKotlinJunit5 by lazy { "test-junit5" }
//    val testKotlinTest by lazy { "org.jetbrains.kotlin:kotlin-test:$kotlinTestVersion" }
//    val testKotlinJUnitTest by lazy { "org.jetbrains.kotlin:kotlin-test-junit:$kotlinTestVersion" }
    val testKotlinTest by lazy { "org.jetbrains.kotlin:kotlin-test" }
    val testKotlinJUnitTest by lazy { "org.jetbrains.kotlin:kotlin-test-junit" }
    val testKOTestAssert by lazy { "io.kotest:kotest-assertions-core-jvm:$koTestVersion" }
    val testKOTestExtJvm by lazy { "io.kotest:kotest-extensions-jvm:$koTestVersion" }
    val testKOTestJunit5Jvm by lazy { "io.kotest:kotest-runner-junit5-jvm:$koTestVersion" }
    val testKOTestKOTestExtSpring by lazy { "io.kotest:kotest-extensions-spring:$koTestKOTestExtSpringVersion" }
    val testKOTestExtArrow by lazy { "io.kotest.extensions:kotest-assertions-arrow:$koTestArrowVersion" }
    val testKOTestExtArrowJvm by lazy { "io.kotest.extensions:kotest-assertions-arrow-jvm:$koTestArrowVersion" }
    val testKOTestExtSpring by lazy { "io.kotest.extensions:kotest-extensions-spring:$koTestExtSpringVersion" }
    val testMockkJvm by lazy { "io.mockk:mockk-jvm:$mockkJvmVersion" }
    val testArrowCore by lazy { "io.arrow-kt:arrow-core-test:$arrowCoreTestVersion" }
    val testMockitoInline by lazy { "org.mockito:mockito-inline:$testMockitoInlineVersion" }
    val testOkHttp by lazy { "com.squareup.okhttp3:okhttp:$testOkHttpVersion" }
    val testOkHttpMockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:$testOkHttpVersion" }
    val testStriKtBom by lazy { "io.strikt:strikt-bom:$striKtVersion" }
    val testStriKtCore by lazy { "io.strikt:strikt-core" }
    val testStriKtJackson by lazy { "io.strikt:strikt-jackson" }
    val testStriKtArrow by lazy { "io.strikt:strikt-arrow" }
    val testStriKtJvm by lazy { "io.strikt:strikt-jvm" }
    val testStriKtMockk by lazy { "io.strikt:strikt-mockk" }
    val testStriKtSpring by lazy { "io.strikt:strikt-spring" }
    val testHttp4k4Strikt by lazy { "org.http4k:http4k-testing-strikt:$testHttp4k4TestVersion" }
    val testHttp4k4Approval by lazy { "org.http4k:http4k-testing-approval:$testHttp4k4TestVersion" }
    val testHttp4k4Hamkrest by lazy { "org.http4k:http4k-testing-hamkrest:$testHttp4k4TestVersion" }
    val testSpringIntegrationTest by lazy {"org.springframework.integration:spring-integration-test"}
    val testKotlinCoroutineTest by lazy {"org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutineTestVersion"}
    val testH2R2DBC by lazy {"io.r2dbc:r2dbc-h2"}
    val testH2DB by lazy {"com.h2database:h2"}
    val testApprovalPath by lazy { "com.approvaltests:approvaltests:$testApprovalVersion" }
}

object ProjectGroupName {
    const val strBase = "com.wellsfargo.str"
    const val strSharedLibraryString = "str.shared.library"
    const val strPluginString = "str.plugin"
    const val strNextGenAppString = "str.nextgen.app"
}

object ProjectConstant {
    const val EMPTY_STRING = ""
    const val plainSuffix = "-plain"
}

object CacheDep {
    val coherence by lazy { "com.oracle.coherence.ce:coherence:${ProjectOtherLibrary.coherenceVersion}" }
    val strCache by lazy {"com.wellsfargo.regcache:str-cache:${ProjectOtherLibrary.strCacheJarVersion}"}
    val csfClient by lazy {"com.wfs.csf.client:CSFClient:${ProjectOtherLibrary.csfClientVersion}"}
}