package com.example.rolling_paper.validator;

import com.example.rolling_paper.annotation.KoreanLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class KoreanLengthValidator implements ConstraintValidator<KoreanLength, String> {
    private int max;

    @Override
    public void initialize(KoreanLength constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null은 허용하지 않는 경우에는 false로 변경
        }

        double length = value.codePoints().mapToDouble(ch -> {
            // 한글인 경우
            if (ch >= 0xAC00 && ch <= 0xD7A3) {
                return 1.0;
            } else {
                return 0.5;
            }
        }).sum();

        return length <= max;
    }
}
