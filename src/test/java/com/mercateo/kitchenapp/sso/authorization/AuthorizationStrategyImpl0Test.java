package com.mercateo.kitchenapp.sso.authorization;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.EnumSet;
import java.util.Set;

import org.apache.wicket.request.component.IRequestableComponent;
import org.junit.Before;
import org.junit.Test;

import com.mercateo.kitchenapp.sso.roles.UserRole;

public class AuthorizationStrategyImpl0Test {

    private AuthorizationStrategyImpl uut;

    @Before
    public void init() {
        initMocks(this);
        uut = new AuthorizationStrategyImpl();
    }

    @NeededRoles(UserRole.ADMIN)
    interface AdminClass extends IRequestableComponent {
        //
    }

    @NeededRoles(UserRole.EDITOR)
    interface EditorClass extends IRequestableComponent {
        //
    }

    interface GeneralClass extends IRequestableComponent {
        //
    }

    @Test
    public void testIsAuthorized_admin() throws Exception {

        // Given
        Set<UserRole> attachedUserRoles = EnumSet.of(UserRole.ADMIN);

        // When

        // Then
        assertTrue(uut.isAuthorized(AdminClass.class, attachedUserRoles));
        assertTrue(uut.isAuthorized(EditorClass.class, attachedUserRoles));
        assertTrue(uut.isAuthorized(GeneralClass.class, attachedUserRoles));

    }

    @Test
    public void testIsAuthorized_editor() throws Exception {

        // Given
        Set<UserRole> attachedUserRoles = EnumSet.of(UserRole.EDITOR);

        // When

        // Then
        assertFalse(uut.isAuthorized(AdminClass.class, attachedUserRoles));
        assertTrue(uut.isAuthorized(EditorClass.class, attachedUserRoles));
        assertTrue(uut.isAuthorized(GeneralClass.class, attachedUserRoles));

    }

    @Test
    public void testIsAuthorized_no_roles() throws Exception {

        // Given
        Set<UserRole> attachedUserRoles = EnumSet.noneOf(UserRole.class);

        // When

        // Then
        assertFalse(uut.isAuthorized(AdminClass.class, attachedUserRoles));
        assertFalse(uut.isAuthorized(EditorClass.class, attachedUserRoles));
        assertTrue(uut.isAuthorized(GeneralClass.class, attachedUserRoles));

    }

}
