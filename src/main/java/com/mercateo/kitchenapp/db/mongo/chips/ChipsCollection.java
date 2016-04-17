package com.mercateo.kitchenapp.db.mongo.chips;

import com.mercateo.kitchenapp.db.mongo.MongoDbCollection;
import com.mongodb.DBCollection;

public class ChipsCollection extends MongoDbCollection {

    public ChipsCollection(DBCollection collection) {
        super(collection);
    }

}
