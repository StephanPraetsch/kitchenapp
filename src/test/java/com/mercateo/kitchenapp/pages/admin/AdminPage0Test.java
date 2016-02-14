package com.mercateo.kitchenapp.pages.admin;

import static org.mockito.Mockito.when;

import java.util.EnumSet;

import org.apache.wicket.Session;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import com.mercateo.kitchenapp.KitchenApp;
import com.mercateo.kitchenapp.pages.WicketTest;
import com.mercateo.kitchenapp.pages.signin.SignInPage;
import com.mercateo.kitchenapp.sso.authorization.UserWebSession;
import com.mercateo.kitchenapp.sso.roles.UserRole;

public class AdminPage0Test extends WicketTest {

    @Test
    public void test_redirect_to_signInPage() {

        // start and render the test page
        tester.startPage(AdminPage.class);

        // assert rendered page class
        tester.assertRenderedPage(SignInPage.class);

    }

    @SuppressWarnings("boxing")
    @Test
    public void test_redirect() {

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
        tester.startPage(AdminPage.class);

        // Then
        tester.assertRenderedPage(SignInPage.class);

    }

    @SuppressWarnings("boxing")
    @Test(expected = UnauthorizedInstantiationException.class)
    public void test_access_denied() {

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
        tester.startPage(AdminPage.class);

        // Then
        // exception

    }

    @SuppressWarnings("boxing")
    @Test
    public void test_access_granted() {

        // Given
        when(authenticator.authenticate(user)).thenReturn(Boolean.TRUE);
        when(userRolesProvider.provide(user)).thenReturn(EnumSet.of(UserRole.ADMIN));

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
        tester.startPage(AdminPage.class);

        // Then
        tester.assertRenderedPage(AdminPage.class);

    }

}
