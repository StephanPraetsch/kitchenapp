package com.mercateo.kitchenapp.data;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Price {

    private final Integer number;

    private final Chip chip;

    public Price(Chip chip) {
        this(Integer.valueOf(1), chip);
    }

    public Price(Integer number, Chip chip) {

        checkNotNull(number);
        checkNotNull(chip);
        checkArgument(number.intValue() > 0, number);

        this.number = number;
        this.chip = chip;

    }

    public Integer getNumber() {
        return number;
    }

    public Chip getChip() {
        return chip;
    }

}
