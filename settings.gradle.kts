pluginManagement{
    repositories {
        maven{
            url = uri("https://maven.aliyun.com/repository/gradle-plugin")
        }
    }
}
rootProject.name = "oas3-redoc-spring-boot-starter"
include(":oas3-redoc-spring-boot-starter")
include(":example-api")
