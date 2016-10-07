package com.mercateo.kitchenapp.data;

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
    private final Price price;

}
