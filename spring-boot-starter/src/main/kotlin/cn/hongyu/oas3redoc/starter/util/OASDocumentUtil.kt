package cn.hongyu.oas3redoc.starter.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.servers.Server
import org.springframework.core.io.ClassPathResource

/**
 * @author hongyu
 * @date 2020/06/14 10:17 AM
 */
data class TagGroup(val name: String, val tags: List<String>)

/**
 * 读取并配置文档的servers
 */
fun configureApiServers(openAPI: OpenAPI, serversConfigJsonPath: String = "oas-document/servers.json") {
    println("Replace servers config with file $serversConfigJsonPath")
    val configFile = ClassPathResource(serversConfigJsonPath).inputStream
    val servers: List<Server> = ObjectMapper().readValue(configFile)
    openAPI.servers = servers
}

/**
 * 添加x-tagGroups扩展
 * 手动添加的原因是由于swagger-annotation的局限性，它提供的注解无法添加value为array的extension,所以这里手动做替换
 */
fun configureTagGroups(openAPI: OpenAPI, tagGroupDef: List<TagGroup>) {
    println("Add extension: x-tagGroups")
    if (openAPI.extensions == null) {
        openAPI.extensions = mutableMapOf()
    }
    // swagger-annotation提供的注解无法添加value为array的extension,所有手动加进去
    // 该extension将被redoc使用，渲染菜单栏时，按此分组
    openAPI.extensions["x-tagGroups"] = tagGroupDef
}

/**
 * 配置文档的description
 * 读取description并配置，同时description中也提供了占位符，可以动态插入其他md文件到description内容中。
 * 例如: description中的@document/a.md@会被替换为resources目录下document/a.md的实际内容
 */
fun configureApiDescription(openAPI: OpenAPI, descriptionMdPath: String = "oas-document/api-description.md") {
    println("Start to configure document description")
    // 定义@filePath@格式的占位符
    val mdReplacementRegex: Regex = """@\S+@""".toRegex()
    // 从资源文件夹中加载description内容
    var description: String = readClassPathResourceAsString(descriptionMdPath) ?: ""
    // 如果找到占位符，则替换
    mdReplacementRegex
            .findAll(description)
            .map { it.value }
            .onEach { println("Found document placeholder $it") }
            .forEach {
                val path = it.replace("@", "")
                val content = readClassPathResourceAsString(path)
                if (content == null) {
                    println("File $path does not exists in resource folder, skip replacement and remove placeholder")
                    description = description.replace(it, "")
                    return@forEach
                }
                println("Replace placeholder $it with file `$path`'s content")
                description = description.replace(it, content)
            }
    // 最后，配置处理完毕的description
    openAPI.info.description = description
}