package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.HashMap;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Meal;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MealsToMongoTransformer implements Function<Meal, DBObject> {

    @Override
    public DBObject apply(Meal meal) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbMealsConstants.TITLE, meal.getTitle());
                put(MongoDbMealsConstants.DESCRIPTION, meal.getDescription());
                put(MongoDbMealsConstants.PRICE, meal.getPrice().getChip().name());
            }

        });

        return dbUserObject;

    }

}
