package com.mercateo.kitchenapp.db.dummy;

import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.UserAccessCreationException;
import com.mercateo.kitchenapp.db.UserAccessFactory;

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
