package com.mercateo.kitchenapp.db.mongo.subscriptions;

import java.util.HashMap;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Subscription;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class SubscriptionsToMongoTransformer implements Function<Subscription, DBObject> {

    @Override
    public DBObject apply(Subscription s) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbSubscriptionConstants.DAY, day(s));
                put(MongoDbSubscriptionConstants.MEAL, meal(s));
            }

        });

        return dbUserObject;

    }

    private String day(Subscription s) {
        return MongoDbSubscriptionConstants.FORMATTER.format(s.getDay());
    }

    private String meal(Subscription s) {
        return s.getMeal();
    }

}
