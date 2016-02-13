package com.mercateo.sso;

import com.mercateo.WicketGuiceHelper;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.layout.HeaderMiddleFooterTemplate;

public class SignInPage extends HeaderMiddleFooterTemplate {

    public SignInPage() {
        UserAccessFactory userAccessFactory = WicketGuiceHelper.get().getInstance(
                UserAccessFactory.class);
        add(new SignInForm("signInForm", userAccessFactory));
        add(new SignUpForm("signUpForm", userAccessFactory));
    }

}
