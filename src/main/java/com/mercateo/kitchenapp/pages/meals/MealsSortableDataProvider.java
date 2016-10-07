package com.mercateo.kitchenapp.pages.meals;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.MealsDao;

public class MealsSortableDataProvider extends SortableDataProvider<Meal, MealField> {

    private final MealsDao meals;

    public MealsSortableDataProvider(MealsDao meals) {
        this.meals = meals;
        setSort(MealField.TITLE, SortOrder.ASCENDING);
    }

    @Override
    public Iterator<Meal> iterator(long first, long count) {

        return meals.get() //
                .sorted(new MealComparator(getSort())) //
                .skip(first) //
                .limit(count) //
                .iterator();

    }

    @Override
    public long size() {
        return meals.get().count();
    }

    @Override
    public IModel<Meal> model(Meal m) {
        return new MealModel(meals, m);
    }

}
