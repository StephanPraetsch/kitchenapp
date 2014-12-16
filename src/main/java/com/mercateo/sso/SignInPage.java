package com.mercateo.sso;

import com.mercateo.db.UserAccessFactoryCache;
import com.mercateo.layout.HeaderMiddleFooterTemplate;

public class SignInPage extends HeaderMiddleFooterTemplate {

    public SignInPage() {
        add(new SignInForm("signInForm", UserAccessFactoryCache.get()));
        add(new SignUpForm("signUpForm", UserAccessFactoryCache.get()));
    }

}
