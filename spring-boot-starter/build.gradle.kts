plugins {
    java
    `maven-publish`
    kotlin("jvm")
    kotlin("plugin.spring")
}
dependencies {
    api("org.springframework.boot:spring-boot-starter")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("org.jetbrains.kotlin:kotlin-reflect")
    api("org.springdoc:springdoc-openapi-ui:1.4.1")
}

publishing {
    repositories {
        maven {
            val publishTarget = "$buildDir/repos"
            val releasesRepoUrl = "${publishTarget}/repository/maven-releases/"
            val snapshotsRepoUrl = "${publishTarget}/repository/maven-snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}