package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.HashMap;
import java.util.function.Function;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MealsSelectionToMongoTransformer implements Function<MealsSelection, DBObject> {

    @Override
    public DBObject apply(MealsSelection meal) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                meal.getTitle().ifPresent(t -> put(MongoDbMealsConstants.TITLE, t));
            }

        });

        return dbUserObject;

    }

}
