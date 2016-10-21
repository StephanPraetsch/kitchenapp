package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDate;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public abstract class OfferPanel extends Panel {

    private final LocalDate day;

    OfferPanel(String id, LocalDate day) {
        super(id);
        this.day = day;

    }

    @Override
    protected void onBeforeRender() {

        super.onBeforeRender();

        add(new Label("offerDay", String.valueOf(day)));

        ListView<String> lv = new ListView<String>("offerMeals", getMeals()) {

            @Override
            protected void populateItem(ListItem<String> item) {
                Label label = new Label("offerTitle", item.getModel());
                label.add(new AttributeAppender("class", new Model<>("checked"), " "));
                item.add(label);
            }

        };

        add(lv);

    }

    abstract List<String> getMeals();

}
