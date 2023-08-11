package com.dhbinh.yummybites.base.security.entity;

import com.dhbinh.yummybites.base.exception.ErrorMessage;

public enum Role {

    ROLE_OWNER("ROLE_OWNER"),
    ROLE_WAITER("ROLE_WAITER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_COOK("ROLE_COOK"),
    ROLE_JANITOR("ROLE_JANITOR"),
    ROLE_MAID("ROLE_MAID"),
    ;

    final String roleName;

    Role(String role_name) {
        this.roleName = role_name;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
