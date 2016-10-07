package com.mercateo.kitchenapp.pages.meals;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Price;
import com.mercateo.kitchenapp.db.MealsDao;

public class MealsTable extends DefaultDataTable<Meal, MealField> {

    private static final long serialVersionUID = 1L;

    public MealsTable(String id, MealsDao meals) {
        super(id, columns(), new MealsSortableDataProvider(meals), 10);
    }

    private static List<IColumn<Meal, MealField>> columns() {

        List<IColumn<Meal, MealField>> columns = new ArrayList<>();

        Model<String> title = new Model<>(MealField.TITLE.name());
        String displayedValue = MealField.TITLE.displayedValue();
        MealField sortThisField = MealField.TITLE;

        columns.add(new PropertyColumn<Meal, MealField>( //
                title, sortThisField, displayedValue));

        columns.add(new PropertyColumn<Meal, MealField>( //
                new Model<>("description"), "description"));

        columns.add(new PropertyColumn<Meal, MealField>( //
                new Model<>("price"), "price") {

            @Override
            public IModel<String> getDataModel(IModel<Meal> rowModel) {
                Price price = rowModel.getObject().getPrice();
                return new Model<>(price.getChip().name());
            }

        });

        return columns;

    }

}
