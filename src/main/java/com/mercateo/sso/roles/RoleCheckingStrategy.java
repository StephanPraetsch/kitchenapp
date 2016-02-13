package com.mercateo.sso.roles;

public interface RoleCheckingStrategy {

    boolean hasAnyRole(UserRole... roles);

}
