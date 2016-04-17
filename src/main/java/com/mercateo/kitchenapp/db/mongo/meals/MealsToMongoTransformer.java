package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.HashMap;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Price;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MealsToMongoTransformer implements Function<Meal, DBObject> {

    @Override
    public DBObject apply(Meal meal) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbMealsConstants.TITLE, meal.getTitle());
                put(MongoDbMealsConstants.DESCRIPTION, meal.getDescription());
                put(MongoDbMealsConstants.PRICES, getPrices(meal));
            }

        });

        return dbUserObject;

    }

    private BasicDBList getPrices(Meal meal) {
        BasicDBList list = new BasicDBList();
        for (Price price : meal.getPrices()) {
            if (price.getNumber().equals(Integer.valueOf(1))) {
                list.add(new BasicDBObject(MongoDbMealsConstants.CHIP, price.getChip().getTitle()));
            } else {
                BasicDBList innerList = new BasicDBList();
                innerList.add(new BasicDBObject(MongoDbMealsConstants.CHIP, price.getChip()
                        .getTitle()));
                innerList.add(new BasicDBObject(MongoDbMealsConstants.NUMBER, String.valueOf(price
                        .getNumber())));
                list.add(innerList);
            }
        }
        return list;
    }

}
