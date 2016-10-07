package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.function.Function;

import com.mercateo.kitchenapp.data.Chip;
import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Price;
import com.mongodb.DBObject;

public class MongoToMealsTransformer implements Function<DBObject, Meal> {

    @Override
    public Meal apply(DBObject userDbObject) {

        String title = getTitle(userDbObject);

        String description = getDescription(userDbObject);

        Price price = getPrice(userDbObject);

        return Meal.builder().title(title).description(description).price(price).build();

    }

    private String getTitle(DBObject dbObject) {
        return (String) dbObject.get(MongoDbMealsConstants.TITLE);
    }

    private String getDescription(DBObject dbObject) {
        return (String) dbObject.get(MongoDbMealsConstants.DESCRIPTION);
    }

    private Price getPrice(DBObject dbObject) {

        String chipTitle = (String) dbObject.get(MongoDbMealsConstants.PRICE);
        Chip chip = Chip.valueOf(chipTitle);
        return new Price(chip);

    }

}
