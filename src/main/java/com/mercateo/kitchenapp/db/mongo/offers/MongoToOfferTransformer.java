package com.mercateo.kitchenapp.db.mongo.offers;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mercateo.kitchenapp.data.Offer;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoToOfferTransformer implements Function<DBObject, Offer> {

    @Override
    public Offer apply(DBObject dbObject) {

        String dayString = (String) dbObject.get(MongoDbOffersConstants.DAY);
        LocalDate day = LocalDate.parse(dayString, MongoDbOffersConstants.FORMATTER);

        BasicDBList list = (BasicDBList) dbObject.get(MongoDbOffersConstants.MEALS);
        Set<String> meals = list.stream() //
                .map(o -> ((BasicDBObject) o).get(MongoDbOffersConstants.TITLE)) //
                .map(String::valueOf) //
                .collect(Collectors.toSet());

        return new Offer(day, meals);

    }

}
