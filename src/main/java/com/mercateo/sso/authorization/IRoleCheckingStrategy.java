package com.mercateo.sso.authorization;

public interface IRoleCheckingStrategy {
    boolean hasAnyRole(UserRole... roles);
}
