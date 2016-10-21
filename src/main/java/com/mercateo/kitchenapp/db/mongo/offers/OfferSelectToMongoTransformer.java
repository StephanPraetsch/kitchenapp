package com.mercateo.kitchenapp.db.mongo.offers;

import java.util.HashMap;
import java.util.function.Function;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OfferSelectToMongoTransformer implements Function<OfferSelect, DBObject> {

    @Override
    public DBObject apply(OfferSelect select) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbOffersConstants.DAY, day(select));
            }

        });

        return dbUserObject;

    }

    private String day(OfferSelect select) {
        return MongoDbOffersConstants.FORMATTER.format(select.getDay());
    }

}
