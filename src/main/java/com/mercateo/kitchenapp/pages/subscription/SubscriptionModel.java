package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDate;

import org.apache.wicket.model.LoadableDetachableModel;

import lombok.NonNull;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Subscription;
import com.mercateo.kitchenapp.db.SubscriptionsDao;

public class SubscriptionModel extends LoadableDetachableModel<Subscription> {

    private final SubscriptionsDao dao;

    private final Email email;

    private final LocalDate id;

    public SubscriptionModel(@NonNull SubscriptionsDao dao, @NonNull Email email,
            @NonNull Subscription s) {
        super(s);
        this.dao = dao;
        this.email = email;
        this.id = s.getDay();
    }

    @Override
    protected Subscription load() {
        return dao.get(email, id) //
                .orElseThrow(() -> new RuntimeException("missing Subscription: " + id + " , "
                        + email));
    }

}
