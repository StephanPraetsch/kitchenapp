package com.mercateo.kitchenapp.sso.roles;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.UserDoesNotExistException;

public class UserRolesProvider {

    private static final Logger logger = Logger.getLogger(UserRolesProvider.class);

    private static final Set<UserRole> EMPTY_ROLES = Collections.emptySet();

    private final UserAccess userAccess;

    @Inject
    UserRolesProvider(UserAccess userAccess) {
        this.userAccess = checkNotNull(userAccess);
    }

    public Set<UserRole> provide(User user) {
        try {
            return getUserRoles(user);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private Set<UserRole> getUserRoles(User user) throws UserDoesNotExistException {

        return userAccess.getUserRoles(user);

    }

    private Set<UserRole> handleException(Exception e) {
        logger.warn(e);
        return EMPTY_ROLES;
    }

}
