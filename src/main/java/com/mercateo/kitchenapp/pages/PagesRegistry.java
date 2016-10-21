package com.mercateo.kitchenapp.pages;

import org.apache.wicket.markup.html.WebPage;

import com.mercateo.kitchenapp.pages.admin.AdminPage;
import com.mercateo.kitchenapp.pages.denied.AccessDeniedPage;
import com.mercateo.kitchenapp.pages.error.ErrorPage;
import com.mercateo.kitchenapp.pages.home.HomePage;
import com.mercateo.kitchenapp.pages.meals.MealsPage;
import com.mercateo.kitchenapp.pages.profile.ProfilePage;
import com.mercateo.kitchenapp.pages.signin.SignInPage;
import com.mercateo.kitchenapp.pages.subscription.SubscriptionPage;

public class PagesRegistry {

    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    public Class<? extends WebPage> getSignInPage() {
        return SignInPage.class;
    }

    public Class<? extends WebPage> getAccessDeniedPage() {
        return AccessDeniedPage.class;
    }

    public Class<? extends WebPage> getProfilePage() {
        return ProfilePage.class;
    }

    public Class<? extends WebPage> getAdminPage() {
        return AdminPage.class;
    }

    public Class<? extends WebPage> getMealsPage() {
        return MealsPage.class;
    }

    public Class<? extends WebPage> getErrorPage() {
        return ErrorPage.class;
    }

    public Class<? extends WebPage> getSubscriptionPage() {
        return SubscriptionPage.class;
    }

}
