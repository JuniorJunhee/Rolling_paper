package com.example.rolling_paper.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = com.example.rolling_paper.validator.ForbiddenWordsValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ForbiddenWords {
    String message() default "금지어가 포함되어 있습니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] words();
}
