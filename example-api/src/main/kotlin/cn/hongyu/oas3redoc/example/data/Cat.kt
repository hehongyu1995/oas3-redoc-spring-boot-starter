package cn.hongyu.oas3redoc.example.data

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Schema(name = "猫")
data class Cat(
        @field:NotBlank
        @field:Schema(description = "名字")
        val name: String,

        @field:Min(0)
        @field:Schema(description = "年龄")
        val age: Int
)