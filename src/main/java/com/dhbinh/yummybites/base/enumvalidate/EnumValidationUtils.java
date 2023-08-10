package com.dhbinh.yummybites.base.enumvalidate;

import com.dhbinh.yummybites.base.exception.ErrorMessage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidationUtils {

    public static <T extends Enum<T>> String generateErrorMessage(Class<? extends Enum<?>> enumClass) {
        List<String> acceptedValues = Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());

        return "Invalid value. Possible values: " + String.join(", ", acceptedValues);
    }
}
