package com.mercateo.kitchenapp.pages.home;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.mercateo.kitchenapp.KitchenApp;

public class HomePageI3Test {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new KitchenApp());
    }

    @Test
    public void homepageRendersSuccessfully() {

        // start and render the test page
        tester.startPage(HomePage.class);

        // assert rendered page class
        tester.assertRenderedPage(HomePage.class);

    }

}
