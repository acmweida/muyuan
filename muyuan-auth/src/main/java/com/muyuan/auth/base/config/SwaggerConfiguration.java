package com.muyuan.auth.base.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class SwaggerConfiguration {

    @Bean(value = "userApi")
    @Order(value = 1)
    public OpenAPI groupRestApi() {
        return new OpenAPI(SpecVersion.V31)
                .info(new Info().title("认证服务")
                        .description("用户认证相关接口")
                        .contact(new Contact().name("weida").url("").email("2456910384@qq.com"))
                        .version("1.0")
                );
//                .host("http://127.0.0.1:20001")
//                .pathMapping("/api/auth")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.muyuan.auth.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .securityContexts(Lists.newArrayList(securityContext()))
//                .securitySchemes(Lists.newArrayList(apiKey()));
    }

//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("BearerToken", "Authorization", "header");
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
//    }
}
