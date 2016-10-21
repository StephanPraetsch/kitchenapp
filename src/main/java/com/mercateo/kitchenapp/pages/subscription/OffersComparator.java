package com.mercateo.kitchenapp.pages.subscription;

import java.util.Comparator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import lombok.NonNull;

import com.mercateo.kitchenapp.data.Offer;

class OffersComparator implements Comparator<Offer> {

    private final SortParam<OfferField> sort;

    OffersComparator(@NonNull SortParam<OfferField> sort) {
        this.sort = sort;
    }

    @Override
    public int compare(@NonNull Offer o1, @NonNull Offer o2) {

        int compare = o1.getDay().compareTo(o2.getDay());

        if (sort.isAscending()) {
            compare = -compare;
        }

        return compare;

    }

}
