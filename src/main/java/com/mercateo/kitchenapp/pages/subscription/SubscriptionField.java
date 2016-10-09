package com.mercateo.kitchenapp.pages.subscription;

enum SubscriptionField {

    DAY("day");

    private final String displayedValue;

    private SubscriptionField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    String displayedValue() {
        return displayedValue;
    }

}
