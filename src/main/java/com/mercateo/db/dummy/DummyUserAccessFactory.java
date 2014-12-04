package com.mercateo.db.dummy;

import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactory;

public class DummyUserAccessFactory implements UserAccessFactory {

    public static final DummyUserAccessFactory INSTANCE = new DummyUserAccessFactory();

    private DummyUserAccessFactory() {
        // hide constructor
    }

    @Override
    public UserAccess create() throws UserAccessCreationException {
        return DummyUserAccess.INSTANCE;
    }

}
