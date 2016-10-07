package com.mercateo.kitchenapp.db;

import java.time.LocalDate;
import java.util.Optional;

import com.mercateo.kitchenapp.data.Offer;

public interface OffersDao {

    public Optional<Offer> get(LocalDate day);

}
