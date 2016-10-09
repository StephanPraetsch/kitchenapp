package com.mercateo.kitchenapp.pages.subscription;

import java.util.Comparator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.mercateo.kitchenapp.data.Subscription;

public class SubscriptionComparator implements Comparator<Subscription> {

    private final SortParam<SubscriptionField> sort;

    public SubscriptionComparator(SortParam<SubscriptionField> sort) {
        this.sort = sort;
    }

    @Override
    public int compare(Subscription o1, Subscription o2) {

        int compare = o1.getDay().compareTo(o2.getDay());

        if (sort.isAscending()) {
            compare = -compare;
        }

        return compare;

    }

}
