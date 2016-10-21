package com.mercateo.kitchenapp.data;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
public class Offer {

    private final LocalDate day;

    private final Set<String> meals;

    private final Set<Email> subscribed;

}
