package com.mercateo.kitchenapp.db.mongo.offer;

import java.util.HashMap;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Offer;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OfferToMongoTransformer implements Function<Offer, DBObject> {

    @Override
    public DBObject apply(Offer offer) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbOfferConstants.DAY, day(offer));
                put(MongoDbOfferConstants.MEALS, meals(offer));
                put(MongoDbOfferConstants.SUBSCRIBED, subscribed(offer));
            }

        });

        return dbUserObject;

    }

    private String day(Offer offer) {
        return MongoDbOfferConstants.FORMATTER.format(offer.getDay());
    }

    private BasicDBList meals(Offer offer) {
        BasicDBList list = new BasicDBList();
        offer.getMeals().ifPresent(set -> set.stream() //
                .map(title -> new BasicDBObject(MongoDbOfferConstants.TITLE, title)) //
                .forEach(list::add));
        return list;
    }

    private BasicDBList subscribed(Offer offer) {
        BasicDBList list = new BasicDBList();
        offer.getSubscribed().ifPresent(set -> set.stream() //
                .map(mail -> new BasicDBObject(MongoDbOfferConstants.SUBSCRIBED, mail.asString())) //
                .forEach(list::add));
        return list;
    }

}
