package com.mercateo.kitchenapp.pages.subscription;

public enum SubscriptionField {

    DAY("day");

    private final String displayedValue;

    private SubscriptionField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String displayedValue() {
        return displayedValue;
    }

}
