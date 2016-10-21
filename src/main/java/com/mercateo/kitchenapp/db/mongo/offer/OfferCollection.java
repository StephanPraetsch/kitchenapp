package com.mercateo.kitchenapp.db.mongo.offer;

import com.mercateo.kitchenapp.db.mongo.MongoDbCollection;
import com.mongodb.DBCollection;

public class OfferCollection extends MongoDbCollection {

    public OfferCollection(DBCollection collection) {
        super(collection);
    }

}
