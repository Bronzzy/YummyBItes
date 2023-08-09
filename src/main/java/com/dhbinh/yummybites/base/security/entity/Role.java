package com.dhbinh.yummybites.base.security.entity;

import com.dhbinh.yummybites.base.exception.ErrorMessage;

public enum Role {

    ROLE_OWNER("ROLE_OWNER"),
    ROLE_WAITER("ROLE_WAITER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_COOK("ROLE_COOK"),
    ;

    public static final String ROLE_NAME_OWNER = "ROLE_OWNER";
    public static final String ROLE_NAME_WAITER = "ROLE_WAITER";
    public static final String ROLE_NAME_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_NAME_COOK = "ROLE_COOK";

    final String roleName;

    Role(String role_name) {
        this.roleName = role_name;
    }

    @Override
    public String toString() {
        return roleName;
    }

    private static final String ROLE_NAMES = String.join(", ",
            Role.ROLE_NAME_OWNER,
            Role.ROLE_NAME_WAITER,
            Role.ROLE_NAME_MANAGER,
            Role.ROLE_NAME_COOK
    );

    public static final String ENUM_VALID_VALUE = ErrorMessage.ENUM_VALID_VALUE + " " + ROLE_NAMES;

//    public static String getAllRoles() {
//        List<String> roleStrings = new ArrayList<>();
//        for (Role role : Role.values()) {
//            roleStrings.add(role.toString());
//        }
//        return String.join(",", roleStrings);
//    }

//    public static class Constants {
//        public static final String ROLE_OWNER = "ROLE_OWNER";
//        public static final String ROLE_WAITER = "ROLE_WAITER";
//        public static final String ROLE_MANAGER = "ROLE_MANAGER";
//        public static final String ROLE_COOK = "ROLE_COOK";
//
//    }

}
