package com.mercateo.kitchenapp.pages.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

import com.mercateo.kitchenapp.data.Meal;

public class MealsTable extends DefaultDataTable<Meal, MealField> {

    public MealsTable(String id, ISortableDataProvider<Meal, MealField> dataProvider) {
        super(id, columns(), dataProvider, 10);
    }

    private static List<IColumn<Meal, MealField>> columns() {

        List<IColumn<Meal, MealField>> columns = new ArrayList<>();

        Model<String> title = new Model<>(MealField.TITLE.name());
        String displayedValue = MealField.TITLE.displayedValue();
        MealField sortThisField = MealField.TITLE;

        columns.add(new PropertyColumn<Meal, MealField>( //
                title, sortThisField, displayedValue));

        columns.add(new PropertyColumn<Meal, MealField>( //
                new Model<String>("description"), "description"));

        columns.add(new PropertyColumn<Meal, MealField>( //
                new Model<String>("prices"), "prices"));

        return columns;

    }

}
