package com.mercateo.kitchenapp.pages.signin;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.forms.SignInForm;
import com.mercateo.kitchenapp.forms.SignUpForm;
import com.mercateo.kitchenapp.pages.general.GeneralPage;

public class SignInPage extends GeneralPage {

    public SignInPage() {
        add(WicketGuiceHelper.get().getInstance(SignInForm.class));
        add(WicketGuiceHelper.get().getInstance(SignUpForm.class));
    }

}
