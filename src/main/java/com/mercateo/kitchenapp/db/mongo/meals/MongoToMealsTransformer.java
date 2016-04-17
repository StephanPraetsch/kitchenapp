package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mercateo.kitchenapp.data.Chip;
import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Price;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoToMealsTransformer implements Function<DBObject, Meal> {

    @Override
    public Meal apply(DBObject userDbObject) {

        String title = getTitle(userDbObject);

        String description = getDescription(userDbObject);

        Set<Price> prices = getPrices(userDbObject);

        return Meal.builder().title(title).description(description).prices(prices).build();

    }

    private String getTitle(DBObject dbObject) {
        return (String) dbObject.get(MongoDbMealsConstants.TITLE);
    }

    private String getDescription(DBObject dbObject) {
        return (String) dbObject.get(MongoDbMealsConstants.DESCRIPTION);
    }

    private Set<Price> getPrices(DBObject dbObject) {

        BasicDBList list = (BasicDBList) dbObject.get(MongoDbMealsConstants.PRICES);

        return list.stream().map(o -> {
            BasicDBObject l = (BasicDBObject) o;
            String chipTitle = (String) l.get(MongoDbMealsConstants.CHIP);
            Chip chip = Chip.builder().title(chipTitle).price(666f).build();
            Object numberObject = l.get(MongoDbMealsConstants.NUMBER);
            if (numberObject != null) {
                Integer number = Integer.valueOf((String) numberObject);
                return new Price(number, chip);
            } else {
                return new Price(chip);
            }
        }).collect(Collectors.toSet());

    }

}
