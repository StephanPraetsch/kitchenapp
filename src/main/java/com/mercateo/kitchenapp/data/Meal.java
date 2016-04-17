package com.mercateo.kitchenapp.data;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Meal {

    @NonNull
    private final String title;

    private final String description;

    @NonNull
    private final Set<Price> prices;

}
