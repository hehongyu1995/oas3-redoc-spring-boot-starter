package cn.hongyu.oas3redoc.starter

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author hongyu
 * @date 2020/06/09 6:00 PM
 */
@Configuration
@ComponentScan(basePackageClasses = [AutoConfiguration::class])
class AutoConfiguration