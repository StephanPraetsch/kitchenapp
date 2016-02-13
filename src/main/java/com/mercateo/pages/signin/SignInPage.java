package com.mercateo.pages.signin;

import com.mercateo.WicketGuiceHelper;
import com.mercateo.forms.SignInForm;
import com.mercateo.forms.SignUpForm;
import com.mercateo.layout.webpage.HeaderMiddleFooterTemplate;

public class SignInPage extends HeaderMiddleFooterTemplate {

    public SignInPage() {
        add(WicketGuiceHelper.get().getInstance(SignInForm.class));
        add(WicketGuiceHelper.get().getInstance(SignUpForm.class));
    }

}
