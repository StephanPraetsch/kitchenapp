package com.mercateo.kitchenapp.sso.roles;

public interface RoleCheckingStrategy {

    boolean hasAnyRole(UserRole... roles);

}
