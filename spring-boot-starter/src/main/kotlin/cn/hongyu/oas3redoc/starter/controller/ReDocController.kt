package cn.hongyu.oas3redoc.starter.controller

import cn.hongyu.oas3redoc.starter.util.readClassPathResourceAsString
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

/**
 * 提供redoc文档
 * @author hongyu
 * @date 2020/06/12 8:24 AM
 */
@RestController
@RequestMapping("/doc")
@Hidden
class ReDocController {

    private val redocPageValue: String = readClassPathResourceAsString("redoc.html")
            ?: throw IllegalStateException("redoc.html not found")

    @PostConstruct
    fun postConstruct() {
        println("""
            ---
            Swagger UI is enabled at endpoint `/swagger-ui.html`
            ReDoc is enabled at endpoint `/doc`
            ---
        """)
    }

    /**
     * 直接返回html类型的文本让浏览器渲染
     */
    @GetMapping(produces = [MediaType.TEXT_HTML_VALUE])
    fun renderReDocHtml(): String {
        return redocPageValue
    }

}