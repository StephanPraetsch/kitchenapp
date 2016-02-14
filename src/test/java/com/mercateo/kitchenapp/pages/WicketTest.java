package com.mercateo.kitchenapp.pages;

import static org.mockito.MockitoAnnotations.initMocks;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import com.mercateo.kitchenapp.KitchenApp;
import com.mercateo.kitchenapp.sso.authorization.Authenticator;
import com.mercateo.kitchenapp.sso.roles.UserRolesProvider;

public class WicketTest {

    @Mock
    protected Authenticator authenticator;

    @Mock
    protected UserRolesProvider userRolesProvider;

    protected WicketTester tester;

    @Before
    public void setUp() {
        initMocks(this);
        tester = new WicketTester(new KitchenApp());
    }

    @After
    public void tearDown() {
        tester.destroy();
    }

}
