package com.mercateo.kitchenapp.pages.editor;

import org.apache.wicket.model.LoadableDetachableModel;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.Meals;

public class MealModel extends LoadableDetachableModel<Meal> {

    private final Meals meals;

    private final String title;

    public MealModel(Meals meals, Meal m) {
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
