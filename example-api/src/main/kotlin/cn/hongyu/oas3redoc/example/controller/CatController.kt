package cn.hongyu.oas3redoc.example.controller

import cn.hongyu.oas3redoc.example.annotation.SecuritySchemeConstants
import cn.hongyu.oas3redoc.example.data.Cat
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cat")
@Tag(name = "猫咪登记", description = "登记各种主子")
@SecurityRequirement(name = SecuritySchemeConstants.BearerAuthentication)
class CatController {

    private val cats = mutableListOf<Cat>()

    @GetMapping
    @Operation(summary = "列出所有猫咪", description = "列出所有已登记的猫咪")
    fun listDogs(): List<Cat> = cats

    @PostMapping
    @Operation(summary = "添加猫咪",description = "登记一只猫咪")
    @ApiResponse(description = "是否登记成功")
    fun addDog(@RequestBody cat: Cat): Boolean{
        cats.add(cat)
        return true
    }
}