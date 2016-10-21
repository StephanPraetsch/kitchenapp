package com.mercateo.kitchenapp.db.mongo.meals;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MealsSelection {

    private final String title;

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

}
