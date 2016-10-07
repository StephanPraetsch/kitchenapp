package com.mercateo.kitchenapp.pages.offers;

import java.time.LocalDate;

import org.apache.wicket.model.LoadableDetachableModel;

import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.db.OffersDao;

public class OfferModel extends LoadableDetachableModel<Offer> {

    private final OffersDao offers;

    private final LocalDate id;

    public OfferModel(OffersDao offers, Offer offer) {
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
