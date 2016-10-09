package com.mercateo.kitchenapp.db.mongo.subscriptions;

import com.mercateo.kitchenapp.db.mongo.MongoDbCollection;
import com.mongodb.DBCollection;

public class SubscriptionsCollection extends MongoDbCollection {

    public SubscriptionsCollection(DBCollection collection) {
        super(collection);
    }

}
