package com.mercateo.kitchenapp.db.mongo.offer;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Email;
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
                meals(offer).ifPresent(meals -> put(MongoDbOfferConstants.MEALS, meals));
                subscribed(offer).ifPresent(s -> put(MongoDbOfferConstants.SUBSCRIBED, s));
            }

        });

        return dbUserObject;

    }

    private String day(Offer offer) {
        return MongoDbOfferConstants.FORMATTER.format(offer.getDay());
    }

    private Optional<BasicDBList> meals(Offer offer) {

        Optional<Set<String>> meals = offer.getMeals();
        if (!meals.isPresent()) {
            return Optional.empty();
        }

        BasicDBList list = new BasicDBList();
        meals.get().stream() //
                .map(title -> new BasicDBObject(MongoDbOfferConstants.TITLE, title)) //
                .forEach(list::add);

        return Optional.of(list);

    }

    private Optional<BasicDBList> subscribed(Offer offer) {

        Optional<Set<Email>> subscribed = offer.getSubscribed();
        if (!subscribed.isPresent()) {
            return Optional.empty();
        }

        BasicDBList list = new BasicDBList();
        subscribed.get().stream() //
                .map(mail -> new BasicDBObject(MongoDbOfferConstants.SUBSCRIBED, mail.asString())) //
                .forEach(list::add);

        return Optional.of(list);

    }

}
