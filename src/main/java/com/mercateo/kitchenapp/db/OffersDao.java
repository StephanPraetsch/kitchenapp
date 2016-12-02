package com.mercateo.kitchenapp.db;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Offer;

public interface OffersDao {

    public Optional<Offer> get(LocalDate day);

    public List<Offer> get(LocalDate from, LocalDate to);

    public void add(LocalDate day, Meal meal);

}
