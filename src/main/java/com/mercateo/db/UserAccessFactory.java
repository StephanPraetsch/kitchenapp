package com.mercateo.db;

import java.io.Serializable;

/**
 * subclasses must implement {@link Serializable}
 */
public interface UserAccessFactory {

    public static final String USER_ACCESS_FACTORY = "USER_ACCESS_FACTORY";

    public UserAccess create() throws UserAccessCreationException;

}
