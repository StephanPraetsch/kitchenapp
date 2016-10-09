package com.mercateo.kitchenapp.pages.meals;

import java.util.Comparator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.mercateo.kitchenapp.data.Meal;

class MealComparator implements Comparator<Meal> {

    private final SortParam<MealField> sort;

    MealComparator(SortParam<MealField> sort) {
        this.sort = sort;
    }

    @Override
    public int compare(Meal m1, Meal m2) {

        MealField property = sort.getProperty();

        int compare = 0;

        switch (property) {
        case DESCRIPTION:
            compare = m1.getDescription().compareTo(m2.getDescription());
            break;
        case PRICES:
            throw new RuntimeException("unsupported price sort");
        case TITLE:
            compare = m1.getTitle().compareTo(m2.getTitle());
            break;
        default:
            throw new RuntimeException("unknown meal field: " + property);
        }

        if (sort.isAscending()) {
            compare = -compare;
        }

        return compare;

    }

}
