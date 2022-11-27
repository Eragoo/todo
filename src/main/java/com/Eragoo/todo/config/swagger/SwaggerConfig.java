package com.Eragoo.todo.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Collections.singletonList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final TypeResolver typeResolver;

    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    Docket swaggerDocket() {
        return new Docket(SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
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
                .securitySchemes(singletonList(authToken()))
                .securityContexts(singletonList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Eragoo"))
                .build();
    }

    private ApiKey authToken() {
        return new ApiKey("Authorization Token", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] scopes = {scope};
        return singletonList(new SecurityReference("Authorization Token", scopes));
    }
}
