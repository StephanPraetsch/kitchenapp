package com.mercateo.kitchenapp.pages.signin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.general.GeneralPage;

public class SignInPage extends GeneralPage {

    private static final long serialVersionUID = 1L;

    public SignInPage(PageParameters params) {
        super(params);
    }

    @Override
    protected void onBeforeRender() {

        super.onBeforeRender();

        add(new SignInForm());
        add(new SignUpForm());

    }

}
