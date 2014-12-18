package com.mercateo.sso.authorization;

import java.util.Collections;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mercateo.db.EmailDoesNotExistException;
import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactoryCache;
import com.mercateo.profile.Email;

public class UserRolesProvider {

    private static final Logger logger = Logger.getLogger(UserRolesProvider.class);

    private static final Set<UserRole> EMPTY_ROLES = Collections.emptySet();

    public Set<UserRole> provide(Email email) {

        try {
            return getRoles(email);
        } catch (UserAccessCreationException | EmailDoesNotExistException e) {
            return handleException(e);
        }

    }

    private Set<UserRole> getRoles(Email email) throws UserAccessCreationException, EmailDoesNotExistException {
        return getUserRoles(email);
    }

    private Set<UserRole> getUserRoles(Email email) throws UserAccessCreationException,
            EmailDoesNotExistException {

        UserAccess userAccess = UserAccessFactoryCache.get().create();

        return userAccess.getUserRoles(email);

    }

    private Set<UserRole> handleException(Exception e) {
        logger.warn(e);
        return EMPTY_ROLES;
    }

}
