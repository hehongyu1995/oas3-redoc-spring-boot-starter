plugins {
    `maven-publish`
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}
dependencies{
    implementation(project(":oas3-redoc-spring-boot-starter"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-ui:1.4.1")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
    jvmTarget = "1.8"
}