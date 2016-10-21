package com.mercateo.kitchenapp.pages.subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.mercateo.kitchenapp.data.Offer;

public class OfferPanel extends Panel {

    OfferPanel(String id, Offer offer) {
        super(id);

        add(new Label("offerDay", String.valueOf(offer.getDay())));

        List<String> titles = new ArrayList<>(offer.getMeals().orElse(Collections.emptySet()));

        ListView<String> lv = new ListView<String>("offerMeals", titles) {

            @Override
            protected void populateItem(ListItem<String> item) {
                Label label = new Label("offerTitle", item.getModel());
                label.add(new AttributeAppender("class", new Model<>("checked"), " "));
                item.add(label);
            }

        };

        add(lv);

    }

}
