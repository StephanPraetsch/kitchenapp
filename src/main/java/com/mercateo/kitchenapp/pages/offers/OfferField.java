package com.mercateo.kitchenapp.pages.offers;

public enum OfferField {

    DAY("day");

    private final String displayedValue;

    private OfferField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String displayedValue() {
        return displayedValue;
    }

}
