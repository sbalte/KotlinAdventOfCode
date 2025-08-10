package com.wellsfargo.str.project

import com.wellsfargo.str.gradle.GradleExtension.developmentOnly
import com.wellsfargo.str.gradle.GradleExtension.implementation
import com.wellsfargo.str.gradle.GradleExtension.runtimeOnly
import com.wellsfargo.str.gradle.GradleExtension.testImplementation
import com.wellsfargo.str.project.OtherLibraryDep.apacheLog4jKotlin
import com.wellsfargo.str.project.OtherLibraryDep.http4kNetty
import com.wellsfargo.str.project.OtherLibraryDep.openJdkJmhAnnProcess
import com.wellsfargo.str.project.OtherLibraryDep.openJdkJmhCore
import com.wellsfargo.str.project.ProjectOtherLibrary.http4kBomVersion
import com.wellsfargo.str.project.StrGeneratedBuildProject.strGeneratedProjectName
import com.wellsfargo.str.project.StrLibraryDependency.TestImplType.*
import com.wellsfargo.str.project.StrSharedBuildProject.strSharedProjectName
import com.wellsfargo.str.project.TestDep.testApprovalPath
import com.wellsfargo.str.project.TestDep.testArrowCore
import com.wellsfargo.str.project.TestDep.testH2DB
import com.wellsfargo.str.project.TestDep.testH2R2DBC
import com.wellsfargo.str.project.TestDep.testHttp4k4Approval
import com.wellsfargo.str.project.TestDep.testHttp4k4Hamkrest
import com.wellsfargo.str.project.TestDep.testHttp4k4Strikt
import com.wellsfargo.str.project.TestDep.testJunitJupiter
import com.wellsfargo.str.project.TestDep.testKOTestAssert
import com.wellsfargo.str.project.TestDep.testKOTestExtArrow
import com.wellsfargo.str.project.TestDep.testKOTestExtArrowJvm
import com.wellsfargo.str.project.TestDep.testKOTestExtJvm
import com.wellsfargo.str.project.TestDep.testKOTestExtSpring
import com.wellsfargo.str.project.TestDep.testKOTestJunit5Jvm
import com.wellsfargo.str.project.TestDep.testKOTestKOTestExtSpring
import com.wellsfargo.str.project.TestDep.testKotlinCoroutineTest
import com.wellsfargo.str.project.TestDep.testKotlinJunit5
import com.wellsfargo.str.project.TestDep.testMockitoInline
import com.wellsfargo.str.project.TestDep.testMockkJvm
import com.wellsfargo.str.project.TestDep.testOkHttp
import com.wellsfargo.str.project.TestDep.testOkHttpMockWebServer
import com.wellsfargo.str.project.TestDep.testSpringBootReactor
import com.wellsfargo.str.project.TestDep.testSpringBootStarter
import com.wellsfargo.str.project.TestDep.testSpringBootTestContainer
import com.wellsfargo.str.project.TestDep.testSpringIntegrationTest
import com.wellsfargo.str.project.TestDep.testStriKtArrow
import com.wellsfargo.str.project.TestDep.testStriKtBom
import com.wellsfargo.str.project.TestDep.testStriKtCore
import com.wellsfargo.str.project.TestDep.testStriKtJackson
import com.wellsfargo.str.project.TestDep.testStriKtJvm
import com.wellsfargo.str.project.TestDep.testStriKtMockk
import com.wellsfargo.str.project.TestDep.testStriKtSpring
//import gradle.kotlin.dsl.accessors._b50274935e55e8b5c1ce69d3499a506a.testFixturesApi
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

object StrLibraryDependency {
    private fun springLibDependency(configuration: DependencyHandler) {
        with(configuration) {
            implementation(SpringDep.springBootActuator)
            implementation(SpringDep.springBootDataRedis)
            implementation(SpringDep.springBootDataRedisReactive)
            implementation(SpringDep.springBootWebflux)
            implementation(SpringDep.springBootR2DBC)
            implementation(SpringDep.springBootIntegrationR2DBC)
            implementation(SpringDep.springBootIntegrationWebflux)
            implementation(SpringDep.springBootIntegration)
            implementation(SpringDep.springBootLog4j2)
            implementation(SpringDep.springModulith)
            implementation(SpringDep.springBootIntegrationJms)
            implementation(SpringDep.springBootIntegrationFile)
            implementation(SpringDep.springBootMail)
            modules {
                module("org.springframework.boot:spring-boot-starter-logging") {
                    replacedBy("org.springframework.boot:spring-boot-starter-log4j2", "Use Log4j2 instead of Logback")
                }
            }
            //jackson
            implementation(SpringDep.jacksonKotlinModule)
            implementation(SpringDep.jacksonDataFormatCsv)
            implementation(SpringDep.jacksonDataFormatXml)
            implementation(SpringDep.jacksonDataFormatYaml)
            implementation(SpringDep.jacksonAnnotation)
            //dev tools
            developmentOnly(SpringDep.springBootDevTool)
            implementation(SpringDep.springBootStarterSecurity)

            implementation(SpringDep.jacksonAnnotation)
        }
    }
    private fun kotlinLibDependency(configuration: DependencyHandler) {
        with(configuration) {
            implementation(platform(KotlinDep.arrowStack))
            implementation(KotlinDep.arrowCore)
            implementation(KotlinDep.arrowFxCoroutine)
            implementation(KotlinDep.arrowSTM)
            implementation(KotlinDep.arrowEval)
            implementation(KotlinDep.arrowAutoClose)
            implementation(SpringDep.jacksonKotlinModule)
            implementation(SpringDep.reactorKotlinExtension)
            implementation(KotlinDep.kotlinReflect)
            implementation(KotlinDep.kotlinCoroutineReactor)
            implementation(KotlinDep.kotlinImmutableCollection)
            implementation(KotlinDep.kotlinHtmlJvm)
            implementation(KotlinDep.kotlinXmlBuilder)
            implementation(KotlinDep.kosonJsonBuilder)
            implementation(KotlinDep.kotlinDataframe)
            implementation(KotlinDep.kotlinKMath)
            implementation(KotlinDep.kotlinMultiKDefault)
            implementation(KotlinDep.kotlinMultiKCore)
            implementation(KotlinDep.kotlinDatetime)
            implementation(KotlinDep.kotlinIOCore)
            implementation(KotlinDep.kotlinRedux)
            implementation(KotlinDep.kotlinxBenchmark)
            implementation(KotlinDep.klibsFlowExt)
        }
    }
    private fun otherLibDependency(configuration: DependencyHandler) {
        with(configuration) {
            implementation(OtherLibraryDep.radisson)
            //mutiny
            implementation(OtherLibraryDep.reactiveMutiny)
            implementation(OtherLibraryDep.reactiveMutinyReactor)
            implementation(OtherLibraryDep.reactiveMutinyKotlin)
            //apache
            implementation(OtherLibraryDep.apacheCommonsLang3)
            implementation(OtherLibraryDep.apacheLog4jKotlin)
            implementation(OtherLibraryDep.apacheVelocity)
            implementation(OtherLibraryDep.apacheVelocityTool)
            //template engines
            implementation(OtherLibraryDep.bsdFreemakerTemplateEngine)
            implementation(OtherLibraryDep.thymeleafTemplateEngine)
            implementation(OtherLibraryDep.thymeleafSpringTemplateEngine)
            implementation(OtherLibraryDep.apachePoi)
            implementation(OtherLibraryDep.apachePoiOoxml)
            implementation(OtherLibraryDep.hubbardsXPath)
            //google
            implementation(OtherLibraryDep.googleGuava)
            implementation(OtherLibraryDep.googleGson)
            //solace
            implementation(OtherLibraryDep.solaceJmsJakarta)
            implementation(OtherLibraryDep.solaceJcsmp)
            implementation(OtherLibraryDep.solaceMsgClient)
            //xml tool
            implementation(OtherLibraryDep.xmlUnitCore)
            //other
            implementation(OtherLibraryDep.vavr)
            implementation(OtherLibraryDep.jasypt)
            implementation(OtherLibraryDep.jaywayJsonPath)
            implementation(platform(OtherLibraryDep.http4kBom))
            implementation(OtherLibraryDep.http4kCore)
            implementation(OtherLibraryDep.http4kMultipart)
            implementation(OtherLibraryDep.http4kWebSocketServer)
            implementation(http4kNetty)
            implementation(OtherLibraryDep.http4kJettyServer)
            implementation(OtherLibraryDep.http4kJackson)
            implementation(OtherLibraryDep.http4kXml)
            implementation(OtherLibraryDep.http4kJacksonXml)
            implementation(OtherLibraryDep.http4kClientOkHttp)
            implementation(OtherLibraryDep.http4kWebSocketClient)
            implementation(OtherLibraryDep.http4kClientApache)
            implementation(OtherLibraryDep.http4kJettyClient)
            implementation(OtherLibraryDep.http4kFreeMarkerTemplate)
            implementation(OtherLibraryDep.http4kThymeleafTemplate)
        }
    }

    enum class TestImplType {
        testImpl, testImplTestFixture, testImplKotlin, testImplPlatform;
    }

    private fun testLibDependency(configuration: DependencyHandler) {
        with(configuration) {
            listOf(
                strSharedProjectName to testImplTestFixture,
                //temporary app jar inclusion
                "${http4kNetty}:$http4kBomVersion" to testImpl,

                testSpringBootStarter to testImpl,
                testSpringBootTestContainer to testImpl,
                testSpringBootReactor to testImpl,
                testJunitJupiter to testImpl,
                testKotlinJunit5 to testImplKotlin,
//                testKotlinTest to testImpl,
                testKOTestAssert to testImpl,
                testKOTestExtJvm to testImpl,
                testKOTestKOTestExtSpring to testImpl,
                testKOTestJunit5Jvm to testImpl,
                testKOTestExtArrow to testImpl,
                testKOTestExtArrowJvm to testImpl,
                testKOTestExtSpring to testImpl,
                testMockkJvm to testImpl,
                testArrowCore to testImpl,
                testMockitoInline to testImpl,
                testOkHttp to testImpl,
                testOkHttpMockWebServer to testImpl,
                testStriKtBom to testImplPlatform,
                testStriKtCore to testImpl,
                testStriKtSpring to testImpl,
                testStriKtMockk to testImpl,
                testStriKtJvm to testImpl,
                testStriKtArrow to testImpl,
                testStriKtJackson to testImpl,
                testHttp4k4Hamkrest to testImpl,
                testHttp4k4Approval to testImpl,
                testHttp4k4Strikt to testImpl,
                testSpringIntegrationTest to testImpl,
                testKotlinCoroutineTest to testImpl,
                apacheLog4jKotlin to testImpl,
                testH2R2DBC to testImpl,
                testH2DB to testImpl,
                openJdkJmhCore to testImpl,
                openJdkJmhAnnProcess to testImpl,
                testApprovalPath to testImpl
            ).map { p ->
                when (p.second) {
//                    testImpl -> testFixturesApi(p.first)
                    testImplTestFixture -> {}
                    else -> {}
//                    testImplKotlin -> testFixturesApi(kotlin(p.first))
//                    testImplPlatform -> testFixturesApi(platform(p.first))
                }; p
            }.map { p ->
                when (p.second) {
                    testImpl -> testImplementation(p.first)
                    testImplTestFixture -> testImplementation(testFixtures(project(p.first)))
                    testImplKotlin -> testImplementation(kotlin(p.first))
                    testImplPlatform -> testImplementation(platform(p.first))
                }
            }
//            testImplementation(kotlin("test"))
        }
    }
    private fun runtimeLibDependency(configuration: DependencyHandler) {
        with(configuration) {
            runtimeOnly(RuntimeDep.mssqlR2DBC)
        }
    }
    private fun projectDependencyWithoutTest(configuration: DependencyHandler) {
        with(configuration) {
            implementation(project(strGeneratedProjectName))
            springLibDependency(configuration)
            kotlinLibDependency(configuration)
            otherLibDependency(configuration)
            runtimeLibDependency(configuration)
        }
    }
    fun handleProjectDependencies(configuration: DependencyHandler) =
        with(configuration) {
            projectDependencyWithoutTest(this)
            runtimeLibDependency(configuration)
            testLibDependency(configuration)
        }
}