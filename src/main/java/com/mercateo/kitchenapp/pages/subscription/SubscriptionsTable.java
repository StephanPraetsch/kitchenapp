package com.mercateo.kitchenapp.pages.subscription;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

import com.mercateo.kitchenapp.data.Subscription;

public class SubscriptionsTable extends DefaultDataTable<Subscription, SubscriptionField> {

    public SubscriptionsTable(String id,
            ISortableDataProvider<Subscription, SubscriptionField> dataProvider) {
        super(id, columns(), dataProvider, 10);
    }

    private static List<IColumn<Subscription, SubscriptionField>> columns() {

        List<IColumn<Subscription, SubscriptionField>> columns = new ArrayList<>();

        Model<String> title = new Model<>(SubscriptionField.DAY.name());
        String displayedValue = SubscriptionField.DAY.displayedValue();
        SubscriptionField sortThisField = SubscriptionField.DAY;

        columns.add(new PropertyColumn<Subscription, SubscriptionField>( //
                title, sortThisField, displayedValue));

        columns.add(new PropertyColumn<Subscription, SubscriptionField>( //
                new Model<>("meal"), "meal"));

        return columns;

    }

}
