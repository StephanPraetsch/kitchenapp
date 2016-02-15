package com.mercateo.kitchenapp.pages.profile;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.WicketTest;
import com.mercateo.kitchenapp.pages.signin.SignInPage;

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
        when(userAccess.get(any(), any())).thenReturn(Optional.of(user));
        signIn(user);

        // When
        tester.startPage(ProfilePage.class);

        // Then
        tester.assertRenderedPage(ProfilePage.class);

    }

}
