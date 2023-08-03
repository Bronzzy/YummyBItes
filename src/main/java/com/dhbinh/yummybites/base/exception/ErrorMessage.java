package com.dhbinh.yummybites.base.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {

    //RESTAURANT
    public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";
    public static final String KEY_RESTAURANT_NOT_FOUND = "exception.resource.not.found.restaurant";

    public static final String RESTAURANT_NAME_NULL_OR_BLANK = "Restaurant name cannot be null or blank";
    public static final String KEY_RESTAURANT_NAME_NULL_OR_BLANK = "exception.input.validation.restaurant.name.null.or.blank";

    public static final String RESTAURANT_ADDRESS_NULL_OR_BLANK = "Restaurant address cannot be null or blank";
    public static final String KEY_RESTAURANT_ADDRESS_NULL_OR_BLANK = "exception.input.validation.restaurant.address.null.or.blank";

    public static final String RESTAURANT_PHONE_NULL_OR_BLANK = "Restaurant phone cannot be null or blank";
    public static final String KEY_RESTAURANT_PHONE_NULL_OR_BLANK = "exception.input.validation.restaurant.phone.null.or.blank";

    public static final String RESTAURANT_PHONE_WRONG_FORMAT = "Restaurant phone can only contain numbers";
    public static final String KEY_RESTAURANT_PHONE_WRONG_FORMAT = "exception.input.validation.restaurant.phone.wrong.format";

    public static final String RESTAURANT_OPEN_HOUR_NULL_OR_BLANK = "Restaurant open hour cannot be null or blank";
    public static final String KEY_RESTAURANT_OPEN_HOUR_NULL_OR_BLANK = "exception.input.validation.restaurant.open.hour.null.or.blank";

    public static final String RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK = "Restaurant closing hour cannot be null or blank";
    public static final String KEY_RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK = "exception.input.validation.restaurant.closing.hour.null.or.blank";

    //EMPLOYEE
    public static final String EMPLOYEE_FIRST_NAME_NULL_OR_BLANK = "Employee first name cannot be null or blank";
    public static final String KEY_EMPLOYEE_NAME_NULL_OR_BLANK = "exception.input.validation.employee.first.name.null.or.blank";

    public static final String EMPLOYEE_LAST_NAME_NULL_OR_BLANK = "Employee last name cannot be null or blank";
    public static final String KEY_EMPLOYEE_LAST_NAME_NULL_OR_BLANK = "exception.input.validation.employee.last.name.null.or.blank";

    public static final String EMPLOYEE_NAME_CONTAIN_NUMBER = "Name cannot contain numbers";
    public static final String KEY_EMPLOYEE_NAME_CONTAIN_NUMBER = "exception.input.validation.employee.name.contain.numbers";

    public static final String EMPLOYEE_ADDRESS_NULL_OR_BLANK = "Employee address cannot be null or blank";
    public static final String KEY_EMPLOYEE_ADDRESS_NULL_OR_BLANK = "exception.input.validation.employee.address.null.or.blank";

    public static final String EMPLOYEE_PHONE_NULL_OR_BLANK = "Employee phone cannot be null or blank";
    public static final String KEY_EMPLOYEE_PHONE_NULL_OR_BLANK = "exception.input.validation.employee.phone.null.or.blank";

    public static final String EMPLOYEE_PHONE_WRONG_FORMAT = "Employee phone can only contain numbers";
    public static final String KEY_EMPLOYEE_PHONE_WRONG_FORMAT = "exception.input.validation.employee.phone.wrong.format";

    public static final String EMPLOYEE_BASE_SALARY_LESS_THAN_20000 = "Employee salary must be greater than 20000";
    public static final String KEY_EMPLOYEE_BASE_SALARY_LESS_THAN_20000 = "exception.input.validation.employee.base.salary.less.than.20000";

    public static final String EMPLOYEE_EMAIL_NULL_OR_BLANK = "Employee email cannot be null or blank";
    public static final String KEY_EMPLOYEE_EMAIL_NULL_OR_BLANK = "exception.input.validation.employee.email.null.or.blank";

    public static final String EMPLOYEE_EMAIL_WRONG_FORMAT = "Employee email not the right format";
    public static final String KEY_EMPLOYEE_EMAIL_WRONG_FORMAT = "exception.input.validation.employee.email.wrong.format";

    //INGREDIENTS
    public static final String INGREDIENT_NAME_NULL_OR_BLANK = "Ingredient name cannot be null or blank";
    public static final String KEY_INGREDIENT_NAME_NULL_OR_BLANK = "exception.input.validation.ingredient.name.null.or.blank";

    public static final String INGREDIENT_QUANTITY_NULL_OR_BLANK = "Ingredient quantity cannot be null or blank";
    public static final String KEY_INGREDIENT_QUANTITY_NULL_OR_BLANK = "exception.input.validation.ingredient.quantity.null.or.blank";

    public static final String INGREDIENT_QUANTITY_LESS_THAN_ZERO = "Ingredient quantity cannot be less than 0";
    public static final String KEY_INGREDIENT_QUANTITY_LESS_THAN_ZERO = "exception.input.validation.ingredient.quantity.less.than.zero";

    public static final String INGREDIENT_ALREADY_EXIST = "Ingredient is already existed";
    public static final String KEY_INGREDIENT_ALREADY_EXIST = "exception.input.validation.ingredient.already.existed";

    public static final String INGREDIENT_NOT_FOUND = "Ingredient not found";
    public static final String KEY_INGREDIENT_NOT_FOUND = "exception.resource.not.found.ingredient";

    //UTILS
    public static final String KEY_MISSING_PARAMETER = "exception.missing.parameter";

    static Map<String, String> errorKeyAndMessageMap() {
        Map<String, String> errorMap = new HashMap<>();

        //RESTAURANT
        errorMap.put(RESTAURANT_NAME_NULL_OR_BLANK, KEY_RESTAURANT_NAME_NULL_OR_BLANK);
        errorMap.put(RESTAURANT_ADDRESS_NULL_OR_BLANK, KEY_RESTAURANT_ADDRESS_NULL_OR_BLANK);
        errorMap.put(RESTAURANT_PHONE_NULL_OR_BLANK, KEY_RESTAURANT_PHONE_NULL_OR_BLANK);
        errorMap.put(RESTAURANT_PHONE_WRONG_FORMAT, KEY_RESTAURANT_PHONE_WRONG_FORMAT);
        errorMap.put(RESTAURANT_OPEN_HOUR_NULL_OR_BLANK, KEY_RESTAURANT_OPEN_HOUR_NULL_OR_BLANK);
        errorMap.put(RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK, KEY_RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK);

        //EMPLOYEE
        errorMap.put(EMPLOYEE_FIRST_NAME_NULL_OR_BLANK, KEY_EMPLOYEE_NAME_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_LAST_NAME_NULL_OR_BLANK, KEY_EMPLOYEE_LAST_NAME_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_NAME_CONTAIN_NUMBER, KEY_EMPLOYEE_NAME_CONTAIN_NUMBER);
        errorMap.put(EMPLOYEE_ADDRESS_NULL_OR_BLANK, KEY_EMPLOYEE_ADDRESS_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_PHONE_NULL_OR_BLANK, KEY_EMPLOYEE_PHONE_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_PHONE_WRONG_FORMAT, KEY_EMPLOYEE_PHONE_WRONG_FORMAT);
        errorMap.put(EMPLOYEE_BASE_SALARY_LESS_THAN_20000, KEY_EMPLOYEE_BASE_SALARY_LESS_THAN_20000);
        errorMap.put(EMPLOYEE_EMAIL_NULL_OR_BLANK, KEY_EMPLOYEE_EMAIL_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_EMAIL_WRONG_FORMAT, KEY_EMPLOYEE_EMAIL_WRONG_FORMAT);

        //INGREDIENTS
        errorMap.put(INGREDIENT_NAME_NULL_OR_BLANK, KEY_INGREDIENT_NAME_NULL_OR_BLANK);
        errorMap.put(INGREDIENT_QUANTITY_NULL_OR_BLANK, KEY_INGREDIENT_QUANTITY_NULL_OR_BLANK);
        errorMap.put(INGREDIENT_QUANTITY_LESS_THAN_ZERO, KEY_INGREDIENT_QUANTITY_LESS_THAN_ZERO);

        return errorMap;
    }
}
