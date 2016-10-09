package com.mercateo.kitchenapp.db.mongo.subscriptions;

import java.time.LocalDate;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Subscription;
import com.mongodb.DBObject;

public class MongoToSubscriptionTransformer implements Function<DBObject, Subscription> {

    @Override
    public Subscription apply(DBObject dbObject) {

        String dayString = (String) dbObject.get(MongoDbSubscriptionConstants.DAY);
        LocalDate day = LocalDate.parse(dayString, MongoDbSubscriptionConstants.FORMATTER);

        String meal = (String) dbObject.get(MongoDbSubscriptionConstants.MEAL);

        return new Subscription(day, meal);

    }

}
