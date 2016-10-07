package com.mercateo.kitchenapp.db.mongo.offers;

import com.mercateo.kitchenapp.db.mongo.MongoDbCollection;
import com.mongodb.DBCollection;

public class OffersCollection extends MongoDbCollection {

    public OffersCollection(DBCollection collection) {
        super(collection);
    }

}
