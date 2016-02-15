package com.mercateo.kitchenapp.pages.admin;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.EnumSet;
import java.util.Optional;

import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.junit.Test;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.WicketTest;
import com.mercateo.kitchenapp.pages.signin.SignInPage;
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

        // When
        tester.startPage(AdminPage.class);

        // Then
        tester.assertRenderedPage(SignInPage.class);

    }

    @SuppressWarnings("boxing")
    @Test(expected = UnauthorizedInstantiationException.class)
    public void test_access_denied() {

        // Given
        User user = User.builder().email(email).password(password).userRoles(EnumSet.of(
                UserRole.EDITOR)).build();
        when(userAccess.get(any(), any())).thenReturn(Optional.of(user));
        signIn(user);

        // When
        tester.startPage(AdminPage.class);

        // Then
        // exception

    }

    @SuppressWarnings("boxing")
    @Test
    public void test_access_granted() {

        // Given
        User user = User.builder().email(email).password(password).userRoles(EnumSet.of(
                UserRole.ADMIN)).build();
        when(userAccess.get(any(), any())).thenReturn(Optional.of(user));

        // When
        signIn(user);
        tester.startPage(AdminPage.class);

        // Then
        tester.assertRenderedPage(AdminPage.class);

    }

}
