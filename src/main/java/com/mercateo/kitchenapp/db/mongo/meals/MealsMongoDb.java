package com.mercateo.kitchenapp.db.mongo.meals;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.stream.Stream;

import javax.inject.Inject;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.AlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.Meals;

public class MealsMongoDb implements Meals {

    private final MealsCollection collection;

    private final MongoToMealsTransformer toMealTransformer;

    private final MealsToMongoTransformer toDbObjectTransformer;

    @Inject
    MealsMongoDb(MealsCollection collection, MongoToMealsTransformer toMealTransformer,
            MealsToMongoTransformer toDbObjectTransformer) {
        this.collection = checkNotNull(collection);
        this.toMealTransformer = checkNotNull(toMealTransformer);
        this.toDbObjectTransformer = checkNotNull(toDbObjectTransformer);
    }

    @Override
    public void addMeal(Meal meal) throws AlreadyExistsExcpetion {
        collection.insert(toDbObjectTransformer.apply(meal));
    }

    @Override
    public void updateMeal(Meal meal) {
        collection.update(toDbObjectTransformer.apply(meal));
    }

    @Override
    public Stream<Meal> get() {
        return collection.findAll().stream().map(toMealTransformer::apply);
    }

}
