/**
 * 配置Swagger的配置类
 * Swagger是一种用于定义RESTful API的规范和工具集，这里我们使用Swagger来生成API文档
 */
package com.financial.transaction.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类
 * 该类用于定义Swagger的全局配置，包括API的基本信息等
 */
@Configuration
public class SwaggerConfig {
    /**
     * 创建自定义的OpenAPI实例
     * 该方法用于配置Swagger的基本信息，如API的标题、版本和描述
     * 
     * @return OpenAPI 实例，包含了API的基本信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Financial Transaction API")
                .version("1.0")
                .description("API documentation for Financial Transaction Application"));
    }
}
