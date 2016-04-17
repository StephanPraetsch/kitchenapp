package com.mercateo.kitchenapp.db.mongo.users;

import com.mercateo.kitchenapp.db.mongo.MongoDbCollection;
import com.mongodb.DBCollection;

public class UserCollection extends MongoDbCollection {

    public UserCollection(DBCollection userCollection) {
        super(userCollection);
    }

}
