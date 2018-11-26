package com.wangtiansoft.KingDarts.config.swagger;

import com.wangtiansoft.KingDarts.config.auth.AuthToken;
import com.wangtiansoft.KingDarts.constants.Constants;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by weitong on 18/1/3.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        String packageString = StringUtils.removeEnd(ClassUtils.getPackageName(SwaggerConfiguration.class), ".config.swagger") + ".modules.apis";
        ParameterBuilder tokenParameterBuilder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<Parameter>();
        tokenParameterBuilder.name(Constants.kAuth_xAccessToken).description("请求令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameterList.add(tokenParameterBuilder.build());

        Set<String> consumesSet = new HashSet<>();
        consumesSet.add("application/json");

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(packageString))
                .build().globalOperationParameters(parameterList).ignoredParameterTypes(AuthToken.class).consumes(consumesSet).produces(consumesSet);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口服务文档")
                .description("接口服务文档")
                .contact(new Contact("WangtianSoft", "www.wangtiansoft.com", "weitong@wangtiansoft.com"))
                .version("1.0")
                .build();
    }

}
