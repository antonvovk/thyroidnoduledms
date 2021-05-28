import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mapstructVersion = "1.4.2.Final"
val springdocVersion = "1.5.8"

plugins {
    id("org.springframework.boot") version "2.5.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("jvm") version "1.5.0"
    kotlin("plugin.spring") version "1.5.0"
    kotlin("plugin.jpa") version "1.5.0"
    kotlin("kapt") version "1.5.0"
}

group = "com.antonvovk"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    implementation("org.apache.poi", "poi", "5.0.0")
    implementation("org.apache.poi", "poi-ooxml", "5.0.0")

    // Frontend
    runtimeOnly(project(":frontend"))

    // Logger
    implementation("org.slf4j", "slf4j-api", "1.7.30")

    // Spring boot
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken", "jjwt", "0.9.1")

    // Swagger
    implementation("org.springdoc", "springdoc-openapi-ui", springdocVersion)
    implementation("org.springdoc", "springdoc-openapi-kotlin", springdocVersion)

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")

    // Testing
    testImplementation("org.springframework.boot", "spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
    testImplementation("com.ninja-squad", "springmockk", "3.0.1")
    testImplementation("io.kotest", "kotest-assertions-core", "4.5.0")

    // Logger
    implementation("org.slf4j", "slf4j-api", "1.7.30")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    // Mapper
    implementation("org.mapstruct", "mapstruct", mapstructVersion)
    kapt("org.mapstruct", "mapstruct-processor", mapstructVersion)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootWar {
    enabled = true
    archiveFileName.set("thyroid-nodule-dms.war")
}
