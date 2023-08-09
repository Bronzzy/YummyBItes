package com.dhbinh.yummybites.base.enumvalidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
//    private Class<? extends Enum<?>> enumClass;
//
//    @Override
//    public void initialize(ValueOfEnum annotation) {
//        enumClass = annotation.enumClass();
//    }
//
//    @Override
//    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return true;
//        }
//
//        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
//            if (enumValue.toString().equals(value.toString())) {
//                return true;
//            }
//        }
//
//        String errorMessage = EnumValidationUtils.generateErrorMessage(enumClass);
//        context.disableDefaultConstraintViolation();
//        context.buildConstraintViolationWithTemplate(errorMessage)
//                .addConstraintViolation();
//
//        return false;
//    }

    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}

