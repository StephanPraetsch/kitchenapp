package com.mercateo.kitchenapp.sso.roles;

import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.UserAccessCreationException;
import com.mercateo.kitchenapp.db.UserAccessFactory;
import com.mercateo.kitchenapp.db.UserDoesNotExistException;

public class UserRolesProvider {

    private static final Logger logger = Logger.getLogger(UserRolesProvider.class);

    private static final Set<UserRole> EMPTY_ROLES = Collections.emptySet();

    private final UserAccessFactory userAccessFactory;

    @Inject
    UserRolesProvider(UserAccessFactory userAccessFactory) {
        this.userAccessFactory = userAccessFactory;
    }

    public Set<UserRole> provide(User user) {
        try {
            return getUserRoles(user);
        } catch (UserAccessCreationException | UserDoesNotExistException e) {
            return handleException(e);
        }
    }

    private Set<UserRole> getUserRoles(User user) throws UserAccessCreationException,
            UserDoesNotExistException {

        UserAccess userAccess = userAccessFactory.create();

        return userAccess.getUserRoles(user);

    }

    private Set<UserRole> handleException(Exception e) {
        logger.warn(e);
        return EMPTY_ROLES;
    }

}
