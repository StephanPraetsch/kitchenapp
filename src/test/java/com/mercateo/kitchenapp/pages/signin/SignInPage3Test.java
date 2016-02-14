package com.mercateo.kitchenapp.pages.signin;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.junit.Test;

import com.mercateo.kitchenapp.pages.WicketTest;

public class SignInPage3Test extends WicketTest {

    @Test
    public void test_render_page() {

        tester.startPage(SignInPage.class);

        tester.assertRenderedPage(SignInPage.class);
        tester.assertComponent("signInForm", Form.class);
        tester.assertComponent("signInForm:email", TextField.class);
        tester.assertComponent("signInForm:password", PasswordTextField.class);

    }

}
