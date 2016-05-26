package com.mercateo.kitchenapp.pages.editor;

import org.apache.wicket.model.LoadableDetachableModel;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.MealsDao;

public class MealModel extends LoadableDetachableModel<Meal> {

    private final MealsDao meals;

    private final String title;

    public MealModel(MealsDao meals, Meal m) {
        super(m);
        this.meals = meals;
        this.title = m.getTitle();
    }

    @Override
    protected Meal load() {
        return meals.get(title) //
                .orElseThrow(() -> new RuntimeException("missing user: " + title));
    }

}
