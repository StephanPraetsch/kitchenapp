package com.mercateo.kitchenapp.pages.subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mercateo.kitchenapp.data.Offer;

// TODO name
class OfferPanel2 extends OfferPanel {

    private final Offer offer;

    OfferPanel2(String id, Offer offer) {
        super(id, offer.getDay());
        this.offer = offer;
    }

    @Override
    List<String> getMeals() {
        return new ArrayList<>(offer.getMeals().orElse(Collections.emptySet()));
    }

}
