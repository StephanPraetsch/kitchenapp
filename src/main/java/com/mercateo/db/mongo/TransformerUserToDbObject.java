package com.mercateo.db.mongo;

import java.util.HashMap;
import java.util.function.Function;

import com.mercateo.profile.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TransformerUserToDbObject implements Function<User, DBObject> {

    @Override
    public DBObject apply(User user) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, String>() {
            {
                put(MongoDbConstants.EMAIL, user.getEmail().asString());
                put(MongoDbConstants.PASSWORD, user.getPassword().asString());
            }
        });

        return dbUserObject;

    }

}
