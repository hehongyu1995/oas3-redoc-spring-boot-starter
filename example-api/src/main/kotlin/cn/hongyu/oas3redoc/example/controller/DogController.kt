package cn.hongyu.oas3redoc.example.controller

import cn.hongyu.oas3redoc.example.annotation.BearerAuthenticationSecurityScheme
import cn.hongyu.oas3redoc.example.annotation.SecuritySchemeConstants
import cn.hongyu.oas3redoc.example.data.Dog
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dog")
@Tag(name = "犬只登记",description = "登记各种狗子")
@SecurityRequirement(name = SecuritySchemeConstants.BearerAuthentication)
class DogController {

    private val dogs = mutableListOf<Dog>()

    @GetMapping
    @Operation(summary = "列出所有狗狗", description = "列出所有已登记的狗狗")
    fun listDogs(): List<Dog> = dogs

    @PostMapping
    @Operation(summary = "添加狗狗",description = "登记一个新狗狗")
    @ApiResponse(description = "是否添加成功")
    fun addDog(@RequestBody dog: Dog): Boolean{
        dogs.add(dog)
        return true
    }
}