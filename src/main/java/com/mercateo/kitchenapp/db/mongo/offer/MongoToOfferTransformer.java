package com.mercateo.kitchenapp.db.mongo.offer;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.data.Offer.OfferBuilder;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoToOfferTransformer implements Function<DBObject, Offer> {

    @Override
    public Offer apply(DBObject dbObject) {

        OfferBuilder builder = Offer.builder();

        String dayString = (String) dbObject.get(MongoDbOfferConstants.DAY);
        LocalDate day = LocalDate.parse(dayString, MongoDbOfferConstants.FORMATTER);
        builder.day(day);

        BasicDBList list = (BasicDBList) dbObject.get(MongoDbOfferConstants.MEALS);
        if (list != null) {
            Set<String> meals = list.stream() //
                    .map(o -> ((BasicDBObject) o).get(MongoDbOfferConstants.TITLE)) //
                    .map(String::valueOf) //
                    .collect(Collectors.toSet());
            builder.meals(meals);
        }

        BasicDBList subscribedList = (BasicDBList) dbObject.get(MongoDbOfferConstants.SUBSCRIBED);
        if (subscribedList != null) {
            Set<Email> subscribed = subscribedList.stream() //
                    .map(o -> ((BasicDBObject) o).get(MongoDbOfferConstants.SUBSCRIBED)) //
                    .map(String::valueOf) //
                    .map(Email::of) //
                    .collect(Collectors.toSet());
            builder.subscribed(subscribed);
        }

        return builder.build();

    }

}
