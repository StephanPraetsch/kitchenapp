package com.mercateo.kitchenapp.pages.profile;

import java.util.EnumSet;

import org.junit.Test;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.WicketTest;
import com.mercateo.kitchenapp.pages.signin.SignInPage;
import com.mercateo.kitchenapp.sso.roles.UserRole;

public class ProfilePage0Test extends WicketTest {

    @SuppressWarnings("boxing")
    @Test
    public void test_redirect_because_not_signed_in() {

        // Given

        // When
        tester.startPage(ProfilePage.class);

        // Then
        tester.assertRenderedPage(SignInPage.class);

    }

    @SuppressWarnings("boxing")
    @Test
    public void test_access_granted() {

        // Given
        User user = User.builder().email(email).password(password).build();
        signIn(user);

        // When
        tester.startPage(ProfilePage.class);

        // Then
        tester.assertRenderedPage(ProfilePage.class);

    }

    @Test
    public void test_components() {

        // Given
        User user = User.builder().email(email).password(password).userRoles(EnumSet.of(
                UserRole.ADMIN, UserRole.EDITOR)).build();
        signIn(user);

        // When
        tester.startPage(ProfilePage.class);

        // Then
        tester.assertLabel("email", user.getEmail().asString());
        tester.assertLabel("password", user.getPassword().asString());
        tester.assertLabel("userRoles", user.getUserRoles().toString());

    }

}
