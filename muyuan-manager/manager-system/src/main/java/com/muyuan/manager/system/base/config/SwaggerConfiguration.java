//package com.muyuan.manager.system.base.config;
//
//import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
//import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
//import com.google.common.collect.Lists;
//import com.muyuan.common.core.enums.ResponseCode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.ResponseBuilder;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//@Import(BeanValidatorPluginsConfiguration.class)
//public class SwaggerConfiguration {
//
//    private OpenApiExtensionResolver openApiExtensionResolver;
//
//    @Autowired
//    public void setOpenApiExtensionResolver(OpenApiExtensionResolver openApiExtensionResolver) {
//        this.openApiExtensionResolver = openApiExtensionResolver;
//    }
//
//    @Bean(value = "userApi")
//    @Order(value = 1)
//    public Docket groupRestApi() {
//        List<Response> responseCodes = new ArrayList<>();
//        Arrays.stream(ResponseCode.values()).forEach(responseCode -> responseCodes.add(new ResponseBuilder()
//                .code(String.valueOf(responseCode.getCode()))
//                .description(responseCode.getMsg())
//                .build()));
//
//        return new Docket(DocumentationType.OAS_30)
//                .globalResponses(HttpMethod.GET,responseCodes)
//                .globalResponses(HttpMethod.POST,responseCodes)
//                .globalResponses(HttpMethod.PUT,responseCodes)
//                .globalResponses(HttpMethod.DELETE,responseCodes)
//                .groupName("doc-1.0")
//                .apiInfo(groupApiInfo())
//                .pathMapping("/api/manager-system")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.muyuan.manager.system.facade.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .extensions(openApiExtensionResolver.buildExtensions("doc-1.0"))
//                .securityContexts(Lists.newArrayList(securityContext()))
//                .securitySchemes(Lists.newArrayList(apiKey()));
//    }
//
//    private ApiInfo groupApiInfo(){
//        return new ApiInfoBuilder()
//                .title("商城运营平台 - 系统基础服务API")
//                .description("提供运营平台登录，基础数据服务接口")
//                .contact(new Contact("weida", "", "2456910384@qq.com"))
//                .version("1.0")
//                .build();
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("BearerToken","Authorization","header");
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
//    }
//}
