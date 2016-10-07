package com.mercateo.kitchenapp.pages.meals;

import org.apache.wicket.model.LoadableDetachableModel;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.MealsDao;

public class MealModel extends LoadableDetachableModel<Meal> {

    private final MealsDao meals;

    private final String id;

    public MealModel(MealsDao meals, Meal m) {
        super(m);
        this.meals = meals;
        this.id = m.getTitle();
    }

    @Override
    protected Meal load() {
        return meals.get(id) //
                .orElseThrow(() -> new RuntimeException("missing meal: " + id));
    }

}
