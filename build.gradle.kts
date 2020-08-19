plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.3.72" apply false
    kotlin("plugin.spring") version "1.3.72" apply false
    id("org.springframework.boot") version "2.0.5.RELEASE" apply false
}

allprojects{
    group = "cn.hongyu"
    version = "0.0.1"
}

subprojects{
    apply(plugin = "java")
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        // 打包源码
        withSourcesJar()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    dependencies{
        implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:2.0.5.RELEASE"))
    }

    configurations.all {
        resolutionStrategy {
            eachDependency {
                if (requested.group == "org.jetbrains.kotlin") useVersion("1.3.72")
            }
        }
    }

    repositories {
        mavenLocal()
        maven{
            url = uri("https://maven.aliyun.com/repository/public")
        }
//        maven{
//            url = uri("https://hehongyu1995.github.io/maven-repo/maven-releases/")
//        }
    }
}