package com.dhbinh.yummybites.base.enumvalidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValueOfEnum annotation) {
        enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            if (enumValue.toString().equals(value.toString())) {
                return true;
            }
        }

        String errorMessage = EnumValidationUtils.generateErrorMessage(enumClass);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();

        return false;
    }
}

