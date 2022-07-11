package com.Eragoo.todo.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;

import java.lang.reflect.Type;
import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Component
class PageableConvention implements AlternateTypeRuleConvention {

    private final TypeResolver typeResolver;

    PageableConvention(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Override
    public List<AlternateTypeRule> rules() {
        return List.of(
                newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(pageableMixin()))
        );
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private Type pageableMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(
                        String.format("%s.generated.%s",
                                Pageable.class.getPackage().getName(),
                                Pageable.class.getSimpleName())
                )
                .property(property(Integer.class, "page"))
                .property(property(Integer.class, "size"))
                .property(property(Sort.class, "sort"))
                .build();
    }

    private Type sortMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(
                        String.format("%s.generated.%s",
                                Sort.class.getPackage().getName(),
                                Sort.class.getSimpleName())
                )
                .property(property(String.class, "sort"))
                .build();
    }

    private AlternateTypePropertyBuilder property(Class<?> type, String name) {
        return new AlternateTypePropertyBuilder()
                .withName(name)
                .withType(type)
                .withCanRead(true)
                .withCanWrite(true);
    }
}
