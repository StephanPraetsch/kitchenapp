package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDate;

import org.apache.wicket.model.LoadableDetachableModel;

import lombok.NonNull;

import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.db.TimetableDao;

class OfferModel extends LoadableDetachableModel<Offer> {

    private final TimetableDao offers;

    private final LocalDate id;

    OfferModel(@NonNull TimetableDao offers, @NonNull Offer offer) {
        super(offer);
        this.offers = offers;
        this.id = offer.getDay();
    }

    @Override
    protected Offer load() {
        return offers.get(id) //
                .orElseThrow(() -> new RuntimeException("missing offer: " + id));
    }

}
