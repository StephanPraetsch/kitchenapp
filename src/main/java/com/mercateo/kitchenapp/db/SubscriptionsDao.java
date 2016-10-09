package com.mercateo.kitchenapp.db;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Subscription;

public interface SubscriptionsDao {

    public Optional<Subscription> get(Email email, LocalDate day);

    public List<Subscription> get(Email email, LocalDate from, LocalDate to);

}
