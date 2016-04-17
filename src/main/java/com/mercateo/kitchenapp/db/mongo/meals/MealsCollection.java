package com.mercateo.kitchenapp.db.mongo.meals;

import com.mercateo.kitchenapp.db.mongo.MongoDbCollection;
import com.mongodb.DBCollection;

public class MealsCollection extends MongoDbCollection {

    public MealsCollection(DBCollection collection) {
        super(collection);
    }

}
