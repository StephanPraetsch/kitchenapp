package com.mercateo.kitchenapp.pages.signin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.junit.Test;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.WicketTest;

public class SignInPage0Test extends WicketTest {

    @Test
    public void test_render_page() throws Exception {

        // When
        tester.startPage(SignInPage.class);

        // Then
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

    @Test
    public void test_already_signed_in() throws Exception {

        // Given
        User user = User.builder().email(email).password(password).build();
        signIn(user);

        // When
        tester.startPage(SignInPage.class);

        // Then
        tester.assertRenderedPage(SignInPage.class);

    }

}
