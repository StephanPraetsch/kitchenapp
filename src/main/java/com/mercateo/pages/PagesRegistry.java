package com.mercateo.pages;

import org.apache.wicket.markup.html.WebPage;

import com.mercateo.pages.denied.AccessDeniedPage;
import com.mercateo.pages.home.HomePage;
import com.mercateo.pages.signin.SignInPage;
import com.mercateo.profile.ProfilePage;
import com.mercateo.sso.roles.admin.AdminPage;
import com.mercateo.sso.roles.editor.EditorPage;

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
