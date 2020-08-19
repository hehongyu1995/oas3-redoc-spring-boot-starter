package cn.hongyu.oas3redoc.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExampleApiApplication

fun main(args: Array<String>) {
    runApplication<ExampleApiApplication>(*args)
}