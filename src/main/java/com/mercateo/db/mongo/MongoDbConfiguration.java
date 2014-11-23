package com.mercateo.db.mongo;

import java.io.Serializable;

public class MongoDbConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String mongoURIString = "mongodb://localhost";

    private final String dbName = "kitchen";

    private final String collectionNameUsers = "users";

    public String getMongoURIString() {
        return mongoURIString;
    }

    public String getDbName() {
        return dbName;
    }

    public String getCollectionNameUsers() {
        return collectionNameUsers;
    }

}
