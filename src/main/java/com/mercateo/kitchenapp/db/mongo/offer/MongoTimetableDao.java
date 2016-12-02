package com.mercateo.kitchenapp.db.mongo.offer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.data.Offer.OfferBuilder;
import com.mercateo.kitchenapp.db.OffersDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@AllArgsConstructor(onConstructor = @__({ @Inject }))
@Slf4j
public class MongoTimetableDao implements OffersDao {

    private final OfferCollection collection;

    private final OfferToMongoTransformer toMongo;

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
        dayWithRange.put(MongoDbOfferConstants.DAY, range);

        return collection.find(dayWithRange) //
                .stream() //
                .map(toOffer::apply) //
                .collect(Collectors.toList());

    }

    // TODO ugly, make it beautiful!
    @Override
    public void add(LocalDate day, Meal meal) {

        Optional<Offer> optional = get(day);

        if (optional.isPresent()) {
            Offer existing = optional.get();
            Offer update = createUpdate(meal, existing);
            collection.update(toMongo.apply(existing), toMongo.apply(update));
        } else {
            Offer newOffer = Offer.builder().day(day).meals(Collections.singleton(meal
                    .getDescription())).build();
            collection.insert(toMongo.apply(newOffer));
        }

    }

    private Offer createUpdate(Meal meal, Offer existing) {

        OfferBuilder builder = Offer.builder();

        builder.day(existing.getDay());

        Set<String> meals = new HashSet<>(existing.getMeals().orElse(new HashSet<>()));
        meals.add(meal.getDescription());
        builder.meals(meals);

        existing.getSubscribed().ifPresent(builder::subscribed);

        return builder.build();
    }

}
