package com.mercateo.kitchenapp.pages.home;

import org.junit.Test;

import com.mercateo.kitchenapp.pages.WicketTest;

public class HomePageI0Test extends WicketTest {

    @Test
    public void homepageRendersSuccessfully() {

        // start and render the test page
        tester.startPage(HomePage.class);

        // assert rendered page class
        tester.assertRenderedPage(HomePage.class);

    }

}
