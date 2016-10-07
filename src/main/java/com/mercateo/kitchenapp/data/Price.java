package com.mercateo.kitchenapp.data;

import lombok.Data;
import lombok.NonNull;

@Data
public class Price {

    @NonNull
    private final Chip chip;

}
