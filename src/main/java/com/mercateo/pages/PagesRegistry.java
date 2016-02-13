package com.mercateo.pages;

import org.apache.wicket.markup.html.WebPage;

import com.mercateo.HomePage;
import com.mercateo.sso.SignInPage;

public class PagesRegistry {

    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    public Class<? extends WebPage> getSignInPageClass() {
        return SignInPage.class;
    }

}
