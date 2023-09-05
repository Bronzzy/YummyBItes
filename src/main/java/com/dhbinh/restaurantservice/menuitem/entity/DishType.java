package com.dhbinh.restaurantservice.menuitem.entity;

public enum DishType {
    APPETIZERS("APPETIZERS"),
    MAINDISH("MAINDISH"),
    DESSERT("DESSERT"),
    DRINKS("DRINKS");

    final String dishType;

    DishType(String dishType) {
        this.dishType = dishType;
    }

    @Override
    public String toString() {
        return dishType;
    }
}
