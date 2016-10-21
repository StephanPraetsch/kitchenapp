package com.mercateo.kitchenapp.db.mongo.offers;

import java.util.HashMap;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Offer;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OffersToMongoTransformer implements Function<Offer, DBObject> {

    @Override
    public DBObject apply(Offer offer) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbOffersConstants.DAY, day(offer));
                put(MongoDbOffersConstants.MEALS, meals(offer));
            }

        });

        return dbUserObject;

    }

    private String day(Offer offer) {
        return MongoDbOffersConstants.FORMATTER.format(offer.getDay());
    }

    private BasicDBList meals(Offer offer) {
        BasicDBList list = new BasicDBList();
        offer.getMeals().stream() //
                .map(title -> new BasicDBObject(MongoDbOffersConstants.TITLE, title)) //
                .forEach(list::add);
        return list;
    }

}
