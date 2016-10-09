package com.mercateo.kitchenapp.db.mongo.subscriptions;

import java.util.HashMap;
import java.util.function.Function;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class SubscriptionSelectToMongoTransformer implements
        Function<SubscriptionSelect, DBObject> {

    @Override
    public DBObject apply(SubscriptionSelect select) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbSubscriptionConstants.DAY, day(select));
                put(MongoDbSubscriptionConstants.EMAIL, email(select));
            }

        });

        return dbUserObject;

    }

    private String day(SubscriptionSelect select) {
        return MongoDbSubscriptionConstants.FORMATTER.format(select.getDay());
    }

    private String email(SubscriptionSelect select) {
        return select.getEmail().asString();
    }

}
