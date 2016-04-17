package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Price;
import com.mongodb.BasicDBList;
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

        Set<Price> prices = new HashSet<>();

        BasicDBList list = (BasicDBList) dbObject.get(MongoDbMealsConstants.PRICES);
        list.forEach(o -> {
            System.out.println(o);
        });

        return prices;

    }

}
