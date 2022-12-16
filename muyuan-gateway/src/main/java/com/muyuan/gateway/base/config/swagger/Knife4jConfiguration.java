package com.muyuan.gateway.base.config.swagger;

import com.muyuan.common.core.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Knife4jConfiguration
 * Description Knife4jConfiguration
 * @Author 2456910384
 * @Date 2022/2/10 11:08
 * @Version 1.0
 */
@Configuration
public class Knife4jConfiguration {

    @Value("${spring.application.name}")
    private String name;

    @Bean
    public Docket defaultApi2() {
        List<Response> responseCodes = new ArrayList<>();
        Arrays.stream(ResponseCode.values()).forEach(responseCode -> responseCodes.add(new ResponseBuilder()
                .code(String.valueOf(responseCode.getCode()))
                .description(responseCode.getMsg())
                .build()));

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(groupApiInfo(name))
                .globalResponses(HttpMethod.GET,responseCodes)
                .globalResponses(HttpMethod.POST,responseCodes)
                .globalResponses(HttpMethod.PUT,responseCodes)
                .globalResponses(HttpMethod.DELETE,responseCodes)
                //分组名称  想要网关被记录到swagger就不要开分组
//                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo(String name) {
        return new ApiInfoBuilder()
                .title("后端接口文档-" + name)
                .description("<div style='font-size:14px;color:red;'>description</div>")
                .termsOfServiceUrl("N")
                .contact(new Contact("muyuan", "https://juejin.cn/user/352263461681214", "zhn.pop@gmail.com"))
                .version("1.0")
                .build();
    }

}
