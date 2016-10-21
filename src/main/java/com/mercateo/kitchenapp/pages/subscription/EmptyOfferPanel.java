package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class EmptyOfferPanel extends OfferPanel {

    public EmptyOfferPanel(String id, LocalDate day) {
        super(id, day);
    }

    @Override
    List<String> getMeals() {
        return Collections.emptyList();
    }

}
