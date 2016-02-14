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

public class AuthorizationStrategyImpl implements IAuthorizationStrategy {

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
            Class<T> componentClass) {

        NeededRoles annotadedRoles = componentClass.getAnnotation(NeededRoles.class);

        if (annotadedRoles == null) {
            return true;
        }

        EnumSet<UserRole> neededUserRoles = Arrays.stream(annotadedRoles.value()) //
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(UserRole.class)));

        Set<UserRole> attachedUserRoles = AbstractAuthenticatedWebSession.get().getRoles();

        return attachedUserRoles.containsAll(neededUserRoles);

    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {

        Class<?> componentClass = component.getClass();

        NeededActions authorizeAction = componentClass.getAnnotation(NeededActions.class);

        if (authorizeAction != null) {
            throw new IllegalStateException("not implemented");
        }

        return true;

    }

}
