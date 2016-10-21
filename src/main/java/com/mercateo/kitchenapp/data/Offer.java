package com.mercateo.kitchenapp.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Offer implements Serializable {

    @NonNull
    private final LocalDate day;

    private final Set<String> meals;

    private final Set<Email> subscribed;

    public Optional<Set<String>> getMeals() {
        return Optional.ofNullable(meals);
    }

    public Optional<Set<Email>> getSubscribed() {
        return Optional.ofNullable(subscribed);
    }

}
