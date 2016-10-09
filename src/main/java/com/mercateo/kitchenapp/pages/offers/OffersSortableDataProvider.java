package com.mercateo.kitchenapp.pages.offers;

import java.time.LocalDate;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import lombok.NonNull;

import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.db.OffersDao;

class OffersSortableDataProvider extends SortableDataProvider<Offer, OfferField> {

    private final OffersDao offers;

    private final LocalDate from;

    private final LocalDate to;

    OffersSortableDataProvider(@NonNull OffersDao offers, @NonNull LocalDate from,
            @NonNull LocalDate to) {
        this.offers = offers;
        this.from = from;
        this.to = to;
        setSort(OfferField.DAY, SortOrder.DESCENDING);
    }

    @Override
    public Iterator<Offer> iterator(long first, long count) {

        return offers.get(from, to).stream() //
                .sorted(new OffersComparator(getSort())) //
                .skip(first) //
                .limit(count) //
                .iterator();

    }

    @Override
    public long size() {
        return offers.get(from, to).size();
    }

    @Override
    public IModel<Offer> model(Offer object) {
        return new OfferModel(offers, object);
    }

}
