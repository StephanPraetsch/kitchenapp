package com.mercateo.kitchenapp.db;

import java.util.stream.Stream;

import com.mercateo.kitchenapp.data.Meal;

public interface Meals {

    public void addMeal(Meal meal) throws AlreadyExistsExcpetion;

    public void updateMeal(Meal meal);

    public Stream<Meal> get();

}
