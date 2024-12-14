plugins {
	kotlin("jvm") version "2.1.0"
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
	implementation(platform("io.arrow-kt:arrow-stack:1.2.4"))
	implementation("io.arrow-kt:arrow-core")
	implementation("io.arrow-kt:arrow-fx-coroutines")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
