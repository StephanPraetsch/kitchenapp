package com.mercateo.kitchenapp.db.mongo.offers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.db.OffersDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@AllArgsConstructor(onConstructor = @__({ @Inject }))
@Slf4j
public class MongoOffersDao implements OffersDao {

    private final OffersCollection collection;

    private final OffersToMongoTransformer toMongo;

    private final OfferSelectToMongoTransformer selectToMongo;

    private final MongoToOfferTransformer toOffer;

    @Override
    public Optional<Offer> get(LocalDate day) {

        try {

            OfferSelect select = new OfferSelect(day);

            return collection.findOne(selectToMongo.apply(select)).map(toOffer::apply);

        } catch (Exception e) {

            log.error("error while finding one offer: " + day, e);
            return Optional.empty();

        }

    }

    @Override
    public List<Offer> get(LocalDate from, LocalDate to) {

        DBObject range = new BasicDBObject();
        range.put("$gte", DateTimeFormatter.ISO_DATE.format(from));
        range.put("$lte", DateTimeFormatter.ISO_DATE.format(to));

        DBObject dayWithRange = new BasicDBObject();
        dayWithRange.put(MongoDbOffersConstants.DAY, range);

        return collection.find(dayWithRange) //
                .stream() //
                .map(toOffer::apply) //
                .collect(Collectors.toList());

    }

}
