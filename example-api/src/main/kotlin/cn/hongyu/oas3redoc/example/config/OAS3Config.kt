package cn.hongyu.oas3redoc.example.config

import cn.hongyu.oas3redoc.example.annotation.BearerAuthenticationSecurityScheme
import cn.hongyu.oas3redoc.starter.util.TagGroup
import cn.hongyu.oas3redoc.starter.util.configureApiDescription
import cn.hongyu.oas3redoc.starter.util.configureApiServers
import cn.hongyu.oas3redoc.starter.util.configureTagGroups
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.extensions.Extension
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty
import io.swagger.v3.oas.annotations.info.Info
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
        info = Info(
                title = "示例API - 文档",
                version = "1.0.0",
                extensions = [
                    Extension(name = "logo", properties = [
                        ExtensionProperty(
                                name = "url", value = "https://s1.ax1x.com/2020/08/18/dMBXqI.jpg"
                        )
                    ])
                ]
        )
)
@BearerAuthenticationSecurityScheme
@Configuration
class OAS3Config {
    @Bean
    fun customOpenApiCustomizer(): OpenApiCustomiser{
        return OpenApiCustomiser {
            // 添加Server配置
            configureApiServers(it, "oas-document/servers.json")
            // 添加文档介绍
            configureApiDescription(it, "oas-document/api-description.md")
            // 对API进行分组
            configureTagGroups(it, listOf(
                    TagGroup("宠物登记", listOf("猫咪登记","犬只登记"))
            ))
        }
    }
}