package com.Eragoo.todo.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@Import({BeanValidatorPluginsConfiguration.class})
@EnableSwagger2
class SwaggerConfig {

    private final TypeResolver typeResolver;

    SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    Docket swaggerDocket() {
        return new Docket(SWAGGER_2)
            .alternateTypeRules(
                newRule(
                    typeResolver.resolve(Iterable.class, WildcardType.class),
                    typeResolver.resolve(List.class, WildcardType.class)
                ),
                newRule(
                    typeResolver.resolve(Sort.class),
                    typeResolver.resolve(List.class, String.class)
                ),
                newRule(
                        typeResolver.resolve(HttpServletRequest.class),
                        typeResolver.resolve(MultipartFile.class)
                )
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.Eragoo"))
            .build();
    }
}
