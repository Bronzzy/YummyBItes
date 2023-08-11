package com.dhbinh.yummybites.menuitem.entity;

public enum DishType {
    DISH_TYPE_APPETIZERS("DISH_TYPE_APPETIZERS"),
    DISH_TYPE_MAINDISH("DISH_TYPE_MAINDISH"),
    DISH_TYPE_DESSERT("DISH_TYPE_DESSERT"),
    DISH_TYPE_DRINKS("DISH_TYPE_DRINKS");

    final String dishType;

    DishType(String role_name){
        this.dishType = role_name;
    }

    @Override
    public String toString() {
        return dishType;
    }
}
