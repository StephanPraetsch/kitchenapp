package com.mercateo.kitchenapp.pages;

import org.apache.wicket.markup.html.WebPage;

import com.mercateo.kitchenapp.pages.admin.AdminPage;
import com.mercateo.kitchenapp.pages.denied.AccessDeniedPage;
import com.mercateo.kitchenapp.pages.editor.EditorPage;
import com.mercateo.kitchenapp.pages.home.HomePage;
import com.mercateo.kitchenapp.pages.profile.ProfilePage;
import com.mercateo.kitchenapp.pages.signin.SignInPage;

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

    public Class<? extends WebPage> getProfilePage() {
        return ProfilePage.class;
    }

    public Class<? extends WebPage> getAdminPage() {
        return AdminPage.class;
    }

    public Class<? extends WebPage> getEditorPage() {
        return EditorPage.class;
    }

}
