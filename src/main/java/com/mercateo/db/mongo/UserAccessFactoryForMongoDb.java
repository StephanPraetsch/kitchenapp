package com.mercateo.db.mongo;

import java.io.Serializable;
import java.net.UnknownHostException;

import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactory;
import com.mongodb.DBCollection;

public class UserAccessFactoryForMongoDb implements UserAccessFactory, Serializable {

    private static final long serialVersionUID = 1L;

    private final MongoDbConfiguration mongoDbConfiguration;

    public UserAccessFactoryForMongoDb(MongoDbConfiguration mongoDbConfiguration) {
        this.mongoDbConfiguration = mongoDbConfiguration;
    }

    @Override
    public UserAccess create() throws UserAccessCreationException {
        try {
            return newUserAccess();
        } catch (UnknownHostException e) {
            throw newUserAccessCreationException(e);
        }
    }

    private UserAccess newUserAccess() throws UnknownHostException {

        MongoDbObjectCreator mongoDbObjectCreator = new MongoDbObjectCreator(mongoDbConfiguration);

        TransformerDbObjectToUser transformerDbObjectToUser = new TransformerDbObjectToUser();

        TransformerUserToDbObject transformerUserToDbObject = new TransformerUserToDbObject();

        DBCollection mongosUserCollection = mongoDbObjectCreator.getUserCollection();

        UserCollection userCollection = new UserCollection(mongosUserCollection);

        return new UserAccessMongoDb(userCollection, transformerDbObjectToUser,
                transformerUserToDbObject);

    }

    private UserAccessCreationException newUserAccessCreationException(UnknownHostException e) {
        return new UserAccessCreationException(e.getMessage());
    }

}
