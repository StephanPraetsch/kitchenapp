package com.mercateo.kitchenapp.db.mongo;

import java.io.Serializable;

public class MongoDbConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String mongoURIString = "mongodb://localhost";

    private final String dbName = "kitchen";

    private final String collectionNameUsers = "users";

    private final String collectionNameMeals = "meals";

    private final String collectionNameChips = "chips";

    private final String collectionNameOffers = "offers";

    public String getMongoURIString() {
        return mongoURIString;
    }

    public String getDbName() {
        return dbName;
    }

    public String getCollectionNameUsers() {
        return collectionNameUsers;
    }

    public String getCollectionNameMeals() {
        return collectionNameMeals;
    }

    public String getCollectionNameChips() {
        return collectionNameChips;
    }

    public String getCollectionNameOffers() {
        return collectionNameOffers;
    }

    public String getCollectionNameSubscriptions() {
        return "subscriptions";
    }

}
