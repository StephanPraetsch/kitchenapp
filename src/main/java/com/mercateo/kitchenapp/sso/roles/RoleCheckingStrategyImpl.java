package com.mercateo.kitchenapp.sso.roles;

import java.util.Set;

import com.mercateo.kitchenapp.sso.authorization.AbstractAuthenticatedWebSession;

public class RoleCheckingStrategyImpl implements RoleCheckingStrategy {

    @Override
    public final boolean hasAnyRole(UserRole... roles) {
        Set<UserRole> sessionRoles = AbstractAuthenticatedWebSession.get().getRoles();
        if (sessionRoles != null) {
            for (UserRole role : roles) {
                if (sessionRoles.contains(role)) {
                    return true;
                }
            }

        }
        return false;
    }

}
