package com.mercateo.kitchenapp.data;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Chip {

    @NonNull
    private final String title;

    private final float price;

}
