package com.mercateo.kitchenapp.pages.subscription;

enum OfferField {

    DAY("day");

    private final String displayedValue;

    private OfferField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    String displayedValue() {
        return displayedValue;
    }

}
