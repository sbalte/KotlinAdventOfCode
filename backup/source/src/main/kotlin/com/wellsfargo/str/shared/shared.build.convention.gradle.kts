package com.wellsfargo.str.shared

plugins {
    `java-gradle-plugin`
    `java-library`
//    `kotlin-dsl`
//    alias(strLibs)
    id("org.springframework.boot")
//    alias(libs.plugins.springBootPlugin)
//    alias(libs.plugins.springDepMgmtPlugin)
//    alias(libs.plugins.kotlinJvmPlugin)
}

val strLibs = the<VersionCatalogsExtension>()
    .find("strLibs")
    .flatMap { it.findLibrary("springBootPlugin") }
    .get()
val libs = the<VersionCatalogsExtension>().named("libs")
val testLibs = extensions.getByType(VersionCatalogsExtension::class.java).named("testLibs")


dependencies {
    println(">>>>Kotlin Version: ${libs.findVersion("kotlinVersion")}")
    implementation(libs.findBundle("springBootBundle"))
    implementation(libs.findLibrary("springBootActuator"))
    implementation(libs.findBundle("http4kBundle"))
    implementation(libs.findBundle("http4kBundle"))
//    api(libs.bundles.springBootBundle)
//    api(libs.bundles.jacksonBundle)
//    api(libs.bundles.kotlinCoreBundle)
//    api(libs.bundles.arrowBundle)
//    api(libs.bundles.solaceBundle)
//    api(libs.bundles.jaxbBundle)
//    api(libs.bundles.googleBundle)
//    api(libs.bundles.http4kBundle)
//    api(libs.bundles.apacheBundle)
//    api(libs.bundles.mutinyBundle)
//    api(libs.bundles.otherLibsBundle)
//    api(libs.bundles.cacheBundle)
//
//    //compile
//    compileOnly(libs.bundle.jmhBundle)
//
//    // runtime
//    runtimeOnly(libs.bundle.dbRuntimeBundle)
//
//    // test
//    testImplementation(libs.bundles.junitTestBundle)
//    testImplementation(libs.bundles.kotlinTestBundle)
//    testImplementation(libs.bundles.springTestBundle)
//    testImplementation(libs.bundles.kotestBundle)
//    testImplementation(libs.bundles.arrowTestBundle)
//    testImplementation(libs.bundles.http4kTestBundle)
//    testImplementation(libs.bundles.striktTestBundle)
//    testImplementation(libs.bundles.dbTestBundle)
//    testImplementation(libs.bundles.mockTestBundle)
}