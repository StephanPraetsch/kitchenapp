package com.mercateo.sso;

import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import com.mercateo.db.EmailDoesNotExistException;
import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactoryCache;
import com.mercateo.profile.Email;

public class UserRolesProvider {

    private static final Logger logger = Logger.getLogger(UserRolesProvider.class);

    private static final Roles EMPTY_ROLES = new Roles();

    public Roles provide(Email email) {

        try {
            return getRoles(email);
        } catch (UserAccessCreationException | EmailDoesNotExistException e) {
            return handleException(e);
        }

    }

    private Roles getRoles(Email email) throws UserAccessCreationException, EmailDoesNotExistException {

        Set<UserRole> userRoles = getUserRoles(email);

        return transformUserRoleToRoles(userRoles);

    }

    private Set<UserRole> getUserRoles(Email email) throws UserAccessCreationException,
            EmailDoesNotExistException {

        UserAccess userAccess = UserAccessFactoryCache.get().create();

        return userAccess.getUserRoles(email);

    }

    private Roles transformUserRoleToRoles(Set<UserRole> userRoles) {
        Roles roles = new Roles();
        for (UserRole userRole : userRoles) {
            roles.add(userRole.asString());
        }
        return roles;
    }

    private Roles handleException(Exception e) {
        logger.warn(e);
        return EMPTY_ROLES;
    }

}
