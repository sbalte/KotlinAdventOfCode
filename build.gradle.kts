plugins {
	kotlin("jvm") version "2.3.0"
}

group = "com.balte.kotlin.adventofcode"
version = "1.0.0-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	val quiverVersion = "0.5.13"
	implementation(platform("io.arrow-kt:arrow-stack:2.2.1.1"))
	implementation("io.arrow-kt:arrow-core")
	implementation("io.arrow-kt:arrow-eval")
	implementation("io.arrow-kt:arrow-fx-coroutines")
	implementation("io.arrow-kt:arrow-optics")
	implementation("io.github.hoc081098:FlowExt:1.0.0")
	implementation("app.cash.quiver:lib:$quiverVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("app.cash.quiver:lib-test:$quiverVersion")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xcontext-receivers")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
