package cn.hongyu.oas3redoc.starter.util

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.util.FileCopyUtils
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets.UTF_8


internal fun Resource.asString(): String? {
    try {
        InputStreamReader(this.inputStream, UTF_8).use { reader -> return FileCopyUtils.copyToString(reader) }
    } catch (e: IOException) {
        return null
    }
}

internal fun readClassPathResourceAsString(path: String): String? {
    return ClassPathResource(path).asString()
}

fun main() {
    println(readClassPathResourceAsString("redoc.html"))
}