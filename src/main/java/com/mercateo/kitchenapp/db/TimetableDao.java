package com.mercateo.kitchenapp.db;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.mercateo.kitchenapp.data.Offer;

public interface TimetableDao {

    public Optional<Offer> get(LocalDate day);

    public List<Offer> get(LocalDate from, LocalDate to);

}
