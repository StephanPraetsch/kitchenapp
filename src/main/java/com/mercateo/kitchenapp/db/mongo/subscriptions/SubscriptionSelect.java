package com.mercateo.kitchenapp.db.mongo.subscriptions;

import java.time.LocalDate;

import lombok.Data;
import lombok.NonNull;

import com.mercateo.kitchenapp.data.Email;

@Data
public class SubscriptionSelect {

    @NonNull
    private final Email email;

    @NonNull
    private final LocalDate day;

}
