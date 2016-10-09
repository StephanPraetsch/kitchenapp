package com.mercateo.kitchenapp.db.mongo.subscriptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Subscription;
import com.mercateo.kitchenapp.db.SubscriptionsDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@AllArgsConstructor(onConstructor = @__({ @Inject }))
@Slf4j
public class MongoSubscriptionsDao implements SubscriptionsDao {

    private final SubscriptionsCollection collection;

    private final SubscriptionsToMongoTransformer toMongo;

    private final SubscriptionSelectToMongoTransformer selectToMongo;

    private final MongoToSubscriptionTransformer toSubscription;

    @Override
    public Optional<Subscription> get(Email email, LocalDate day) {

        try {

            SubscriptionSelect select = new SubscriptionSelect(email, day);

            return collection.findOne(selectToMongo.apply(select)).map(toSubscription::apply);

        } catch (Exception e) {

            log.error("error while finding one offer: " + day, e);
            return Optional.empty();

        }

    }

    @Override
    public List<Subscription> get(Email email, LocalDate from, LocalDate to) {

        DBObject range = new BasicDBObject();
        range.put("$gte", DateTimeFormatter.ISO_DATE.format(from));
        range.put("$lte", DateTimeFormatter.ISO_DATE.format(to));

        DBObject dayWithRange = new BasicDBObject();
        dayWithRange.put(MongoDbSubscriptionConstants.DAY, range);

        return collection.find(dayWithRange) //
                .stream() //
                .map(toSubscription::apply) //
                .collect(Collectors.toList());

    }

}
