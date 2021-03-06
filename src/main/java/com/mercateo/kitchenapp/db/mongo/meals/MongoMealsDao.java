package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NonNull;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.AlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.MealsDao;
import com.mercateo.kitchenapp.db.mongo.DuplicateFoundException;

public class MongoMealsDao implements MealsDao {

    private static final Logger logger = LoggerFactory.getLogger(MongoMealsDao.class);

    private final MealsCollection collection;

    private final MongoToMealsTransformer toMealTransformer;

    private final MealsToMongoTransformer toDbObjectTransformer;

    private final MealsSelectionToMongoTransformer selectionToDb;

    @Inject
    MongoMealsDao(@NonNull MealsCollection collection,
            @NonNull MongoToMealsTransformer toMealTransformer,
            @NonNull MealsToMongoTransformer toDbObjectTransformer,
            @NonNull MealsSelectionToMongoTransformer selectionToDb) {
        this.collection = collection;
        this.toMealTransformer = toMealTransformer;
        this.toDbObjectTransformer = toDbObjectTransformer;
        this.selectionToDb = selectionToDb;
    }

    @Override
    public Optional<Meal> get(String title) {

        try {

            MealsSelection select = MealsSelection.builder().title(title).build();

            return collection.findOne( //
                    selectionToDb.apply(select)) //
                    .map(toMealTransformer::apply);

        } catch (DuplicateFoundException e) {

            logger.error("error while finding one meal: " + title, e);
            return Optional.empty();

        }

    }

    @Override
    public void addMeal(Meal meal) throws AlreadyExistsExcpetion {
        collection.insert(toDbObjectTransformer.apply(meal));
    }

    @Override
    public Stream<Meal> get() {
        return collection.findAll().stream().map(toMealTransformer::apply);
    }

}
