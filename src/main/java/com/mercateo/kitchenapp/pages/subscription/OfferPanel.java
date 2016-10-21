package com.mercateo.kitchenapp.pages.subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.mercateo.kitchenapp.data.Offer;

public class OfferPanel extends Panel {

    OfferPanel(String id, Optional<Offer> offer) {
        super(id);

        add(new Label("offerDay", offer.map(Offer::getDay).map(String::valueOf).orElse("")));

        List<String> titles = new ArrayList<>(offer.map(Offer::getMeals).orElse(Collections
                .emptySet()));

        ListView<String> lv = new ListView<String>("offerMeals", titles) {

            @Override
            protected void populateItem(ListItem<String> item) {
                item.add(new Label("offerTitle", item.getModel()));
            }

        };

        add(lv);

    }

}
