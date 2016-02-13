package com.mercateo.kitchenapp.pages.signin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.forms.SignInForm;
import com.mercateo.kitchenapp.forms.SignUpForm;
import com.mercateo.kitchenapp.pages.general.GeneralPage;

public class SignInPage extends GeneralPage {

    public SignInPage(PageParameters params) {
        super(params);
        add(WicketGuiceHelper.get().getInstance(SignInForm.class));
        add(WicketGuiceHelper.get().getInstance(SignUpForm.class));
    }

}
