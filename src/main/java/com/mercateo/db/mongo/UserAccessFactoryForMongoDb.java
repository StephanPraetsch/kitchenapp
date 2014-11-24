package com.mercateo.db.mongo;

import java.io.Serializable;
import java.net.UnknownHostException;

import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactory;

public class UserAccessFactoryForMongoDb implements UserAccessFactory, Serializable {

    private static final long serialVersionUID = 1L;

    private final MongoDbConfiguration mongoDbConfiguration;

    public UserAccessFactoryForMongoDb(MongoDbConfiguration mongoDbConfiguration) {
        this.mongoDbConfiguration = mongoDbConfiguration;
    }

    @Override
    public UserAccess create() throws UserAccessCreationException {

        try {
            return new UserAccessMongoDb(new MongoDbObjectCreator(mongoDbConfiguration));
        } catch (UnknownHostException e) {
            throw new UserAccessCreationException(e.getMessage());
        }

    }

}
