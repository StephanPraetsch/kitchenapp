package com.mercateo.kitchenapp.db;

import java.util.Optional;
import java.util.stream.Stream;

import com.mercateo.kitchenapp.data.Meal;

public interface MealsDao {

    public Optional<Meal> get(String title);

    public void addMeal(Meal meal) throws AlreadyExistsExcpetion;

    public void updateMeal(Meal meal);

    public Stream<Meal> get();

}
