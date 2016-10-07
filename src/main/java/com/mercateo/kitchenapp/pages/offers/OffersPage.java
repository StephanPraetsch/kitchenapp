package com.mercateo.kitchenapp.pages.offers;

import java.time.LocalDate;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.db.OffersDao;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;

public class OffersPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    @Inject
    private OffersDao offers;

    // TODO von parametern auslesen
    LocalDate from = LocalDate.now();

    LocalDate to = from.plusDays(5);

    public OffersPage(PageParameters params) {
        super(params);
        add(new Label("from", new Model<>(from)));
        add(new Label("to", new Model<>(to)));
        add(new OffersTable("offersTable", new OffersSortableDataProvider(offers, from, to)));
    }

}
