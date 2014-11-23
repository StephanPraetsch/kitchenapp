package com.mercateo.db.mongo;

import java.io.Serializable;

/**
 * subclasses must implement {@link Serializable}
 */
public interface UserAccessFactory {

    public UserAccess create() throws UserAccessCreationException;

}
