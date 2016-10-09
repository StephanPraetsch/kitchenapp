package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDate;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import lombok.NonNull;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Subscription;
import com.mercateo.kitchenapp.db.SubscriptionsDao;

class SubscriptionsSortableDataProvider extends
        SortableDataProvider<Subscription, SubscriptionField> {

    private final SubscriptionsDao subscriptions;

    private final Email email;

    private final LocalDate from;

    private final LocalDate to;

    public SubscriptionsSortableDataProvider(@NonNull SubscriptionsDao offers, @NonNull Email email,
            @NonNull LocalDate from, @NonNull LocalDate to) {
        this.subscriptions = offers;
        this.email = email;
        this.from = from;
        this.to = to;
        setSort(SubscriptionField.DAY, SortOrder.DESCENDING);
    }

    @Override
    public Iterator<Subscription> iterator(long first, long count) {

        return subscriptions.get(email, from, to).stream() //
                .sorted(new SubscriptionComparator(getSort())) //
                .skip(first) //
                .limit(count) //
                .iterator();

    }

    @Override
    public long size() {
        return subscriptions.get(email, from, to).size();
    }

    @Override
    public IModel<Subscription> model(Subscription object) {
        return new SubscriptionModel(subscriptions, email, object);
    }

}
