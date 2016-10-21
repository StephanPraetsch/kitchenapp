package com.mercateo.kitchenapp.db.mongo;

import java.io.Serializable;

public class MongoDbConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getMongoURIString() {
        return "mongodb://localhost";
    }

    public String getDbName() {
        return "kitchen";
    }

    public String getCollectionNameUsers() {
        return "users";
    }

    public String getCollectionNameMeals() {
        return "meals";
    }

    public String getCollectionNameOffers() {
        return "offers";
    }

}
