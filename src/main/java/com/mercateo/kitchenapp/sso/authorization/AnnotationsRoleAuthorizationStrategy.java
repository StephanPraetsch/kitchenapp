package com.mercateo.kitchenapp.sso.authorization;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

import com.mercateo.kitchenapp.sso.roles.UserRole;

public class AnnotationsRoleAuthorizationStrategy implements IAuthorizationStrategy {

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
            Class<T> componentClass) {

        AuthorizeInstantiation classAnnotation = componentClass.getAnnotation(
                AuthorizeInstantiation.class);

        if (classAnnotation == null) {
            return true;
        }

        EnumSet<UserRole> neededuserRoles = Arrays.stream(classAnnotation.value()) //
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(UserRole.class)));

        Set<UserRole> attachedUserRoles = AbstractAuthenticatedWebSession.get().getRoles();

        return attachedUserRoles.containsAll(neededuserRoles);

    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {

        Class<?> componentClass = component.getClass();

        AuthorizeAction authorizeAction = componentClass.getAnnotation(AuthorizeAction.class);

        if (authorizeAction != null) {
            throw new IllegalStateException("not implemented");
        }

        return true;

    }

}
