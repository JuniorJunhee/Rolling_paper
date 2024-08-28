package com.example.rolling_paper.validator;

import com.example.rolling_paper.annotation.ForbiddenWords;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ForbiddenWordsValidator implements ConstraintValidator<ForbiddenWords, String> {
    private String[] forbiddenWords;

    @Override
    public void initialize(ForbiddenWords constraintAnnotation) {
        this.forbiddenWords = constraintAnnotation.words();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null은 허용하지 않는 경우에는 false로 변경
        }
        return Arrays.stream(forbiddenWords).noneMatch(value::contains);
    }
}
