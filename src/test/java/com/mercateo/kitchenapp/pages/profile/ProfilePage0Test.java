package com.mercateo.kitchenapp.pages.profile;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.mockito.Mock;

import com.mercateo.kitchenapp.KitchenApp;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.WicketTest;
import com.mercateo.kitchenapp.sso.authorization.Authenticator;
import com.mercateo.kitchenapp.sso.authorization.UserWebSession;
import com.mercateo.kitchenapp.sso.roles.UserRole;
import com.mercateo.kitchenapp.sso.roles.UserRolesProvider;

public class ProfilePage0Test extends WicketTest {

    @Mock
    private Authenticator authenticator;

    @Mock
    private UserRolesProvider userRolesProvider;

    @SuppressWarnings("boxing")
    @Test
    public void test() {

        // Given
        User user = User.of(Email.of("email"), Password.of("pw"));
        when(authenticator.authenticate(user)).thenReturn(Boolean.TRUE);
        when(userRolesProvider.provide(user)).thenReturn(Collections.singleton(UserRole.ADMIN));

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

}
