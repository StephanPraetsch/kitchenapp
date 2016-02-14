package com.mercateo.kitchenapp.pages;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;

import com.mercateo.kitchenapp.KitchenApp;

public class WicketTest {

    protected WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new KitchenApp());
    }

    @After
    public void tearDown() {
        tester.destroy();
    }

}
