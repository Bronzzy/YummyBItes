package com.dhbinh.yummybites.base.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {



    //RESTAURANT
    public static final String KEY_RESTAURANT_NOT_FOUND = "exception.resource.not.found.restaurant";
    public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";

    public static final String KEY_RESTAURANT_NAME_ALREADY_USED = "exception.input.validation.restaurant.name.already.used";
    public static final String RESTAURANT_NAME_ALREADY_USED = "Restaurant name already used";

    public static final String KEY_RESTAURANT_ADDRESS_ALREADY_USED = "exception.input.validation.restaurant.address.already.used";
    public static final String RESTAURANT_ADDRESS_ALREADY_USED = "Restaurant address already used";

    public static final String KEY_RESTAURANT_NAME_NULL_OR_BLANK = "exception.input.validation.restaurant.name.null.or.blank";
    public static final String RESTAURANT_NAME_NULL_OR_BLANK = "Restaurant name cannot be null or blank";

    public static final String KEY_RESTAURANT_OPEN_HOUR_NULL_OR_BLANK = "exception.input.validation.restaurant.open.hour.null.or.blank";
    public static final String RESTAURANT_OPEN_HOUR_NULL_OR_BLANK = "Open hour cannot be null or blank";

    public static final String KEY_RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK = "exception.input.validation.restaurant.closing.hour.null.or.blank";
    public static final String RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK = "Closing hour cannot be null or blank";

    //EMPLOYEE
    public static final String KEY_EMPLOYEE_NAME_NULL_OR_BLANK = "exception.input.validation.first.name.null.or.blank";
    public static final String EMPLOYEE_FIRST_NAME_NULL_OR_BLANK = "First name cannot be null or blank";

    public static final String KEY_EMPLOYEE_NULL_OR_BLANK = "exception.input.validation.employee.null.or.blank";
    public static final String EMPLOYEE_NULL_OR_BLANK = "Employee cannot be null or blank";

    public static final String KEY_EMPLOYEE_NOT_FOUND = "exception.resource.not.found.employee";
    public static final String EMPLOYEE_NOT_FOUND = "Employee cannot be found";

    public static final String KEY_EMPLOYEE_LAST_NAME_NULL_OR_BLANK = "exception.input.validation.last.name.null.or.blank";
    public static final String EMPLOYEE_LAST_NAME_NULL_OR_BLANK = "Last name cannot be null or blank";

    public static final String KEY_EMPLOYEE_NAME_CONTAIN_NUMBER = "exception.input.validation.employee.name.contain.numbers";
    public static final String EMPLOYEE_NAME_CONTAIN_NUMBER = "Name cannot contain numbers";

    public static final String KEY_EMPLOYEE_BASE_SALARY_LESS_THAN_20000 = "exception.input.validation.base.salary.less.than.20000";
    public static final String EMPLOYEE_BASE_SALARY_LESS_THAN_20000 = "Base Salary cannot be less than 20000";

    public static final String KEY_EMPLOYEE_EMAIL_ALREADY_EXISTED = "exception.input.validation.employee.email.already.existed";
    public static final String EMPLOYEE_EMAIL_ALREADY_EXISTED = "Employee email already existed";

    //INGREDIENTS
    public static final String KEY_INGREDIENT_NAME_NULL_OR_BLANK = "exception.input.validation.ingredient.name.null.or.blank";
    public static final String INGREDIENT_NULL_OR_BLANK = "Ingredient cannot be null or blank";

    public static final String KEY_INGREDIENT_QUANTITY_NULL_OR_BLANK = "exception.input.validation.ingredient.quantity.null.or.blank";
    public static final String INGREDIENT_QUANTITY_NULL_OR_BLANK = "Ingredient quantity cannot be null or blank";

    public static final String KEY_INGREDIENT_QUANTITY_LESS_THAN_ONE = "exception.input.validation.ingredient.quantity.less.than.one";
    public static final String INGREDIENT_QUANTITY_LESS_THAN_ONE = "Ingredient quantity cannot be less than 1";

    public static final String KEY_INGREDIENT_ALREADY_EXIST = "exception.input.validation.ingredient.already.existed";
    public static final String INGREDIENT_ALREADY_EXIST = "Ingredient is already existed";

    public static final String KEY_INGREDIENT_NOT_FOUND = "exception.resource.not.found.ingredient";
    public static final String INGREDIENT_NOT_FOUND = "Ingredient not found";

    //SUPPLIER
    public static final String KEY_SUPPLIER_NAME_EXIST = "exception.input.validation.supplier.name.exist";
    public static final String SUPPLIER_NAME_EXIST = "Supplier name already existed";

    public static final String KEY_SUPPLIER_NULL_OR_BLANK = "exception.input.validation.supplier.null.or.blank";
    public static final String SUPPLIER_NULL_OR_BLANK = "Supplier cannot be null or blank";

    public static final String KEY_SUPPLIER_NAME_NULL_OR_BLANK = "exception.input.validation.supplier.name.null.or.blank";
    public static final String SUPPLIER_NAME_NULL_OR_BLANK = "Supplier name cannot be null or blank";

    public static final String KEY_SUPPLIER_ADDRESS_EXIST = "exception.input.validation.supplier.address.exist";
    public static final String SUPPLIER_ADDRESS_EXIST = "Supplier address already existed";

    public static final String KEY_SUPPLIER_NOT_FOUND = "exception.resource.not.found.supplier";
    public static final String SUPPLIER_NOT_FOUND = "Supplier cannot be found";

    //MENU ITEMS
    public static final String KEY_DESCRIPTION_NULL_OR_BLANK = "exception.input.validation.description.null.or.blank";
    public static final String DESCRIPTION_NULL_OR_BLANK = "Description cannot be null or blank";

    public static final String KEY_PRICE_NULL_OR_BLANK = "exception.input.validation.price.null.or.blank";
    public static final String PRICE_NULL_OR_BLANK = "Price cannot be null or blank";

    public static final String KEY_PRICE_LESS_THAN_ONE = "exception.input.validation.price.less.than.one";
    public static final String PRICE_LESS_THAN_ONE = "Price cannot be less than 1";

    public static final String KEY_DISH_TYPE_NULL_OR_BLANK = "exception.input.validation.dish.type.null.or.blank";
    public static final String DISH_TYPE_NULL_OR_BLANK = "Dish type cannot be null or blank";

    public static final String KEY_DISH_TYPE_INVALID_VALUE = "exception.input.validation.dish.type.invalid.value";
    public static final String DISH_TYPE_INVALID_VALUE = "Dish type can only be APPETIZERS, MAIN_DISH, DESSERTS or DRINKS";

    public static final String KEY_MENU_ITEM_NOT_FOUND = "exception.resource.not.found.menu.item";
    public static final String MENU_ITEM_NOT_FOUND = "Menu item cannot be found";

    public static final String KEY_MENU_ITEM_ALREADY_EXIST = "exception.input.validation.menu.item.already.existed";
    public static final String MENU_ITEM_ALREADY_EXIST = "Menu item already existed";

    //DINING TABLE
    public static final String KEY_DINING_TABLE_NOT_FOUND = "exception.resource.not.found.dining.table";
    public static final String DINING_TABLE_NOT_FOUND = "Dining table cannot be found";

    public static final String KEY_DINING_TABLE_NULL_OR_BLANK = "exception.input.validation.dining.table.null.or.blank";
    public static final String DINING_TABLE_NULL_OR_BLANK = "Dining table cannot be null or blank";

    public static final String KEY_TABLE_IS_OCCUPIED = "exception.input.validation.table.is.occupied";
    public static final String TABLE_IS_OCCUPIED = "Table is occupied";

    public static final String KEY_DINING_TABLE_NUMBER_MIN = "exception.input.validation.dining.table.number.min";
    public static final String DINING_TABLE_MIN_NUMBER = "Dining table number cannot be less than 1";

    //ORDER
    public static final String KEY_ORDER_NOT_FOUND = "exception.resource.not.found.order";
    public static final String ORDER_NOT_FOUND = "Order cannot be found";

    public static final String KEY_ORDER_NULL_OR_BLANK = "exception.input.validation.order.null.or.blank";
    public static final String ORDER_NULL_OR_BLANK = "Order cannot be null or blank";

    //ODER DETAILS
    public static final String KEY_ORDER_DETAILS_NULL_OR_BLANK = "exception.input.validation.order.details.null.or.blank";
    public static final String ORDER_DETAILS_NULL_OR_BLANK = "Order details cannot be null or blank";

    public static final String KEY_MENU_ITEM_NULL_OR_BLANK = "exception.input.validation.menu.item.null.or.blanks";
    public static final String MENU_ITEM_NULL_OR_BLANK = "Menu item cannot be null or blank";

    public static final String KEY_QUANTITY_NULL_OR_BLANK = "exception.input.validation.quantity.null.or.blank";
    public static final String QUANTITY_NULL_OR_BLANK = "Quantity cannot be null or blank";

    public static final String KEY_QUANTITY_LESS_THAN_ONE = "exception.input.validation.quantity.less.than.one";

    public static final String QUANTITY_LESS_THAN_ONE = "Quantity cannot be less than 1";

    //BILL
    public static final String KEY_BILL_NOT_FOUND = "exception.resource.not.found.bill";
    public static final String BILL_NOT_FOUND = "Bill cannot be found";

    public static final String KEY_BILL_NULL_OR_BLANK = "exception.input.validation.bill.null.or.blank";
    public static final String BILL_NULL_OR_BLANK = "Bill cannot be null or blank";

    //BILL DETAIL

    public static final String KEY_PRICE_PER_UNIT_NULL_OR_BLANK = "exception.input.validation.price.per.unit.null.or.blank";
    public static final String PRICE_PER_UNIT_NULL_OR_BLANK = "Price per unit cannot be null or blank";

    public static final String KEY_BILL_DETAIL_NOT_FOUND = "exception.resource.not.found.bill.detail";
    public static final String BILL_DETAIL_NOT_FOUND = "Bill detail cannot be found";

    public static final String KEY_BILL_DETAIL_NULL_OR_BLANK = "exception.input.validation.bill.detail.null.or.blank";
    public static final String BILL_DETAIL_NULL_OR_BLANK = "Bill detail cannot be null or blank";

    //USER
    public static final String KEY_USERNAME_EXIST = "exception.input.validation.username.exist";
    public static final String USERNAME_EXIST = "Username already exist";

    //RESERVATION
    public static final String KEY_RESERVATION_DATE_MUST_BE_IN_FUTURE = "exception.input.validation.reservation.date.must.be.in.future";
    public static final String RESERVATION_DATE_MUST_BE_IN_FUTURE = "Reservation must be after today";
    public static final String KEY_RESERVATION_NULL = "exception.input.validation.reservation.null";
    public static final String RESERVATION_NULL = "Reservation cannot be null";

    public static final String KEY_GUESTS_NUMBER_LESS_THAN_ONE = "exception.input.validation.guests.number.less.than.one";
    public static final String GUESTS_NUMBER_LESS_THAN_ONE = "Guest number cannot be less than 1";

    //UTILS
    public static final String KEY_MISSING_PARAMETER = "exception.missing.parameter";

    public static final String KEY_NAME_NULL_OR_BLANK = "exception.input.validation.name.null.or.blank";
    public static final String NAME_NULL_OR_BLANK = "Name cannot be null or blank";

    public static final String KEY_PHONE_WRONG_FORMAT = "exception.input.validation.phone.wrong.format";
    public static final String PHONE_WRONG_FORMAT = "Phone can only contains number";

    public static final String KEY_ADDRESS_NULL_OR_BLANK = "exception.input.validation.address.null.or.blank";
    public static final String ADDRESS_NULL_OR_BLANK = "Address cannot be null or blank";

    public static final String KEY_PHONE_NULL_OR_BLANK = "exception.input.validation.phone.null.or.blank";
    public static final String PHONE_NULL_OR_BLANK = "Phone cannot be null or blank";

    public static final String KEY_EMAIL_NULL_OR_BLANK = "exception.input.validation.email.null.or.blank";
    public static final String EMAIL_NULL_OR_BLANK = "Email cannot be null or blank";

    public static final String KEY_EMAIL_WRONG_FORMAT = "exception.input.validation.email.wrong.format";
    public static final String EMAIL_WRONG_FORMAT = "Email not the right format";

    public static final String KEY_PASSWORD_NULL_OR_BLANK = "exception.input.validation.password.null.or.blank";
    public static final String PASSWORD_NULL_OR_BLANK = "Password cannot be null or blank";

    public static final String KEY_PASSWORD_NOT_MATCH_PATTERN = "exception.input.validation.password.not.match.pattern";
    public static final String PASSWORD_NOT_MATCH_PATTERN = "Password must contains at least 1 uppercase character, 1 number and 1 special character";

    public static final String KEY_ENUM_NULL_OR_BLANK = "exception.input.validation.enum.null.or.blank";
    public static final String ENUM_NULL_OR_BLANK = "Enum value cannot be null or blank";

    public static final String KEY_ENUM_INVALID_VALUE = "exception.input.validation.enum.invalid.value";

    public static final String KEY_FILE_NOT_FOUND = "exception.resource.not.found.file";
    public static final String FILE_NOT_FOUND = "File cannot be found";


    static Map<String, String> errorKeyAndMessageMap() {
        Map<String, String> errorMap = new HashMap<>();

        //RESTAURANT
        errorMap.put(RESTAURANT_NAME_NULL_OR_BLANK, KEY_RESTAURANT_NAME_NULL_OR_BLANK);
        errorMap.put(RESTAURANT_OPEN_HOUR_NULL_OR_BLANK, KEY_RESTAURANT_OPEN_HOUR_NULL_OR_BLANK);
        errorMap.put(RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK, KEY_RESTAURANT_CLOSING_HOUR_NULL_OR_BLANK);

        //EMPLOYEE
        errorMap.put(EMPLOYEE_NULL_OR_BLANK, KEY_EMPLOYEE_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_FIRST_NAME_NULL_OR_BLANK, KEY_EMPLOYEE_NAME_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_LAST_NAME_NULL_OR_BLANK, KEY_EMPLOYEE_LAST_NAME_NULL_OR_BLANK);
        errorMap.put(EMPLOYEE_NAME_CONTAIN_NUMBER, KEY_EMPLOYEE_NAME_CONTAIN_NUMBER);
        errorMap.put(EMPLOYEE_BASE_SALARY_LESS_THAN_20000, KEY_EMPLOYEE_BASE_SALARY_LESS_THAN_20000);

        //INGREDIENTS
        errorMap.put(INGREDIENT_NULL_OR_BLANK, KEY_INGREDIENT_NAME_NULL_OR_BLANK);
        errorMap.put(INGREDIENT_QUANTITY_NULL_OR_BLANK, KEY_INGREDIENT_QUANTITY_NULL_OR_BLANK);
        errorMap.put(INGREDIENT_QUANTITY_LESS_THAN_ONE, KEY_INGREDIENT_QUANTITY_LESS_THAN_ONE);

        //SUPPLIER
        errorMap.put(SUPPLIER_NAME_NULL_OR_BLANK, KEY_SUPPLIER_NAME_NULL_OR_BLANK);
        errorMap.put(SUPPLIER_NULL_OR_BLANK, KEY_SUPPLIER_NULL_OR_BLANK);

        //MENU ITEMS
        errorMap.put(DESCRIPTION_NULL_OR_BLANK, KEY_DESCRIPTION_NULL_OR_BLANK);
        errorMap.put(PRICE_NULL_OR_BLANK, KEY_PRICE_NULL_OR_BLANK);
        errorMap.put(PRICE_LESS_THAN_ONE, KEY_PRICE_LESS_THAN_ONE);
        errorMap.put(DISH_TYPE_NULL_OR_BLANK, KEY_DISH_TYPE_NULL_OR_BLANK);
        errorMap.put(DISH_TYPE_INVALID_VALUE, KEY_DISH_TYPE_INVALID_VALUE);

        //DINING TABLE
        errorMap.put(DINING_TABLE_NULL_OR_BLANK, KEY_DINING_TABLE_NULL_OR_BLANK);
        errorMap.put(DINING_TABLE_NOT_FOUND, KEY_DINING_TABLE_NOT_FOUND);
        errorMap.put(DINING_TABLE_MIN_NUMBER,KEY_DINING_TABLE_NUMBER_MIN);

        //ORDER
        errorMap.put(ORDER_NULL_OR_BLANK, KEY_ORDER_NULL_OR_BLANK);

        //ORDER DETAILS
        errorMap.put(MENU_ITEM_NULL_OR_BLANK, KEY_MENU_ITEM_NULL_OR_BLANK);
        errorMap.put(QUANTITY_NULL_OR_BLANK, KEY_QUANTITY_NULL_OR_BLANK);
        errorMap.put(QUANTITY_LESS_THAN_ONE, KEY_QUANTITY_LESS_THAN_ONE);
        errorMap.put(ORDER_DETAILS_NULL_OR_BLANK, KEY_ORDER_DETAILS_NULL_OR_BLANK);

        //BILL
        errorMap.put(KEY_BILL_NOT_FOUND, KEY_BILL_NOT_FOUND);
        errorMap.put(BILL_NULL_OR_BLANK, KEY_BILL_NULL_OR_BLANK);

        //BILL DETAILS
        errorMap.put(PRICE_PER_UNIT_NULL_OR_BLANK, KEY_PRICE_PER_UNIT_NULL_OR_BLANK);
        errorMap.put(BILL_DETAIL_NULL_OR_BLANK, KEY_BILL_DETAIL_NULL_OR_BLANK);

        //RESERVATION
        errorMap.put(KEY_RESERVATION_NULL, RESERVATION_NULL);
        errorMap.put(KEY_RESERVATION_DATE_MUST_BE_IN_FUTURE, RESERVATION_DATE_MUST_BE_IN_FUTURE);
        errorMap.put(KEY_GUESTS_NUMBER_LESS_THAN_ONE, GUESTS_NUMBER_LESS_THAN_ONE);

        //UTILS
        errorMap.put(NAME_NULL_OR_BLANK, KEY_NAME_NULL_OR_BLANK);
        errorMap.put(PHONE_NULL_OR_BLANK, KEY_PHONE_NULL_OR_BLANK);
        errorMap.put(PHONE_WRONG_FORMAT, KEY_PHONE_WRONG_FORMAT);
        errorMap.put(ADDRESS_NULL_OR_BLANK, KEY_ADDRESS_NULL_OR_BLANK);
        errorMap.put(EMAIL_NULL_OR_BLANK, KEY_EMAIL_NULL_OR_BLANK);
        errorMap.put(EMAIL_WRONG_FORMAT, KEY_EMAIL_WRONG_FORMAT);
        errorMap.put(PASSWORD_NULL_OR_BLANK, KEY_PASSWORD_NULL_OR_BLANK);
        errorMap.put(PASSWORD_NOT_MATCH_PATTERN, KEY_PASSWORD_NOT_MATCH_PATTERN);
        errorMap.put(ENUM_NULL_OR_BLANK, KEY_ENUM_NULL_OR_BLANK);
        errorMap.put(FILE_NOT_FOUND, KEY_FILE_NOT_FOUND);

        return errorMap;
    }
}
