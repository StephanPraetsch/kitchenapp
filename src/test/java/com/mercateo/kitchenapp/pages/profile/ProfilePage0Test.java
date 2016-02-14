package com.mercateo.kitchenapp.pages.profile;

import static org.mockito.Mockito.when;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import com.mercateo.kitchenapp.KitchenApp;
import com.mercateo.kitchenapp.pages.WicketTest;
import com.mercateo.kitchenapp.pages.signin.SignInPage;
import com.mercateo.kitchenapp.sso.authorization.UserWebSession;

public class ProfilePage0Test extends WicketTest {

    @SuppressWarnings("boxing")
    @Test
    public void test_no_redirect_because_signed_in() {

        // Given
        when(authenticator.authenticate(user)).thenReturn(Boolean.TRUE);

        tester = new WicketTester(new KitchenApp() {

            @Override
            public Session newSession(Request request, Response response) {
                UserWebSession userWebSession = new UserWebSession(request, authenticator,
                        userRolesProvider);
                userWebSession.signIn(user);
                return userWebSession;
            }

        });

        // When
        tester.startPage(ProfilePage.class);

        // Then
        tester.assertRenderedPage(ProfilePage.class);

    }

    @SuppressWarnings("boxing")
    @Test
    public void test_redirect_because_not_signed_in() {

        // Given
        when(authenticator.authenticate(user)).thenReturn(Boolean.FALSE);

        tester = new WicketTester(new KitchenApp() {

            @Override
            public Session newSession(Request request, Response response) {
                UserWebSession userWebSession = new UserWebSession(request, authenticator,
                        userRolesProvider);
                userWebSession.signIn(user);
                return userWebSession;
            }

        });

        // When
        tester.startPage(ProfilePage.class);

        // Then
        tester.assertRenderedPage(SignInPage.class);

    }

}
