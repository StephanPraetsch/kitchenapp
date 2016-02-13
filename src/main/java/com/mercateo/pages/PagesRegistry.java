package com.mercateo.pages;

import org.apache.wicket.markup.html.WebPage;

import com.mercateo.pages.denied.AccessDeniedPage;
import com.mercateo.pages.home.HomePage;
import com.mercateo.pages.signin.SignInPage;

public class PagesRegistry {

    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    public Class<? extends WebPage> getSignInPageClass() {
        return SignInPage.class;
    }

    public Class<? extends WebPage> getAccessDeniedPage() {
        return AccessDeniedPage.class;
    }

}
