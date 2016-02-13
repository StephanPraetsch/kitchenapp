package com.mercateo.sso.roles;

import java.util.Set;

import com.mercateo.sso.authorization.AbstractAuthenticatedWebSession;
import com.mercateo.sso.authorization.IRoleCheckingStrategy;
import com.mercateo.sso.authorization.UserRole;

public class RoleCheckingStrategyImpl implements IRoleCheckingStrategy {

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
