package com.mercateo.kitchenapp.pages.admin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.mercateo.kitchenapp.KitchenApp;
import com.mercateo.kitchenapp.pages.signin.SignInPage;

public class AdminPage0Test {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new KitchenApp());
    }

    @Test
    public void test_redirect_to_signInPage() {

        // start and render the test page
        tester.startPage(AdminPage.class);

        // assert rendered page class
        tester.assertRenderedPage(SignInPage.class);

    }

    @Test
    public void test_components() {

        // When
        tester.startPage(SignInPage.class);

        // Then
        tester.assertComponent("signInForm", Form.class);
        tester.assertComponent("signInForm:email", TextField.class);
        tester.assertComponent("signInForm:password", PasswordTextField.class);

    }

}
