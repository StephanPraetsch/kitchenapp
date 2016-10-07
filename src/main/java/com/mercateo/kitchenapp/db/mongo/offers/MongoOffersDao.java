package com.mercateo.kitchenapp.db.mongo.offers;

import java.time.LocalDate;
import java.util.Optional;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.db.OffersDao;

@AllArgsConstructor(onConstructor = @__({ @Inject }))
@Slf4j
public class MongoOffersDao implements OffersDao {

    private final OffersCollection collection;

    private final OffersToMongoTransformer toMongo;

    private final MongoToOfferTransformer toOffer;

    @Override
    public Optional<Offer> get(LocalDate day) {

        try {

            // TODO kein null als parameter => neues select-offer-objekt
            Offer offer = new Offer(day, null);

            return collection.findOne(toMongo.apply(offer)).map(toOffer::apply);

        } catch (Exception e) {

            log.error("error while finding one offer: " + day, e);
            return Optional.empty();

        }

    }

}
