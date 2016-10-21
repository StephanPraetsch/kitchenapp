package com.mercateo.kitchenapp.db.mongo.offer;

import java.util.HashMap;
import java.util.function.Function;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class OfferSelectToMongoTransformer implements Function<OfferSelect, DBObject> {

    @Override
    public DBObject apply(OfferSelect select) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbOfferConstants.DAY, day(select));
            }

        });

        return dbUserObject;

    }

    private String day(OfferSelect select) {
        return MongoDbOfferConstants.FORMATTER.format(select.getDay());
    }

}
