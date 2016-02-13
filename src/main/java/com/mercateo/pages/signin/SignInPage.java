package com.mercateo.pages.signin;

import com.mercateo.WicketGuiceHelper;
import com.mercateo.forms.SignInForm;
import com.mercateo.forms.SignUpForm;
import com.mercateo.pages.general.GeneralPage;

public class SignInPage extends GeneralPage {

    public SignInPage() {
        add(WicketGuiceHelper.get().getInstance(SignInForm.class));
        add(WicketGuiceHelper.get().getInstance(SignUpForm.class));
    }

}
