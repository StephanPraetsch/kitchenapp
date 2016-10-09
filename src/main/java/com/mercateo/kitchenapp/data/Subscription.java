package com.mercateo.kitchenapp.data;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Subscription {

    private final LocalDate day;

    private final String meal;

}
