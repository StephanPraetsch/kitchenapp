package com.mercateo.kitchenapp.models.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

import com.mercateo.kitchenapp.data.User;

public class UserTable extends DefaultDataTable<User, UserField> {

    public UserTable(String id, ISortableDataProvider<User, UserField> dataProvider) {
        super(id, columns(), dataProvider, 10);
    }

    private static List<IColumn<User, UserField>> columns() {

        List<IColumn<User, UserField>> columns = new ArrayList<>();

        Model<String> title = new Model<>(UserField.EMAIL.name());
        String displayedValue = UserField.EMAIL.displayedValue();
        UserField sortThisField = UserField.EMAIL;

        columns.add(new PropertyColumn<User, UserField>( //
                title, sortThisField, displayedValue));

        columns.add(new PropertyColumn<User, UserField>( //
                new Model<String>("password"), "password"));

        columns.add(new PropertyColumn<User, UserField>( //
                new Model<String>("roles"), "userRoles"));

        return columns;

    }

}
