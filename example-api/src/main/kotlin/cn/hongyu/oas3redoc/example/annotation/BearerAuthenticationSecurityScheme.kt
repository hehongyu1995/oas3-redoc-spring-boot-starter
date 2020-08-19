package cn.hongyu.oas3redoc.example.annotation

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme

object SecuritySchemeConstants {
    const val BearerAuthentication = "HTTP - Bearer鉴权"
}

@SecurityScheme(
        name = SecuritySchemeConstants.BearerAuthentication,
        description = """
调用接口前，请先申请Token，并将Token以Bearer格式封装放置在**HTTP请求头**的`Authorization`中。
例如：

```http
POST /cat HTTP/1.1
Host: http://localhost:8080
Authorization: Bearer {token}
```
        """,
        type = SecuritySchemeType.HTTP,
        `in` = SecuritySchemeIn.HEADER,
        scheme = "Bearer",
        bearerFormat = "JWT"
)
annotation class BearerAuthenticationSecurityScheme