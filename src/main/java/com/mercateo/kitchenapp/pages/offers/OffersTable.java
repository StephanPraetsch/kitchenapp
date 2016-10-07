package com.mercateo.kitchenapp.pages.offers;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;

import com.mercateo.kitchenapp.data.Offer;

public class OffersTable extends DefaultDataTable<Offer, OfferField> {

    public OffersTable(String id, ISortableDataProvider<Offer, OfferField> dataProvider) {
        super(id, columns(), dataProvider, 10);
    }

    private static List<IColumn<Offer, OfferField>> columns() {

        List<IColumn<Offer, OfferField>> columns = new ArrayList<>();

        Model<String> title = new Model<>(OfferField.DAY.name());
        String displayedValue = OfferField.DAY.displayedValue();
        OfferField sortThisField = OfferField.DAY;

        columns.add(new PropertyColumn<Offer, OfferField>( //
                title, sortThisField, displayedValue));

        columns.add(new PropertyColumn<Offer, OfferField>( //
                new Model<>("meals"), "meals"));

        return columns;

    }

}
