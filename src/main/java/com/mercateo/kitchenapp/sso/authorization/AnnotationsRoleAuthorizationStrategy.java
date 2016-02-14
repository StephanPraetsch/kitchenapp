package com.mercateo.kitchenapp.sso.authorization;

import java.util.EnumSet;
import java.util.Set;

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

        EnumSet<UserRole> neededuserRoles = EnumSet.noneOf(UserRole.class);

        for (UserRole userRoleAtClass : classAnnotation.value()) {
            neededuserRoles.add(userRoleAtClass);
        }

        Set<UserRole> attachedUserRoles = AbstractAuthenticatedWebSession.get().getRoles();

        return attachedUserRoles.containsAll(neededuserRoles);

    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {
        return isActionAuthorized(component.getClass(), action);
    }

    protected boolean isActionAuthorized(Class<?> componentClass, Action action) {
        if (!check(action, componentClass.getAnnotation(AuthorizeAction.class))) {
            return false;
        }

        final AuthorizeActions authorizeActionsAnnotation = componentClass.getAnnotation(
                AuthorizeActions.class);
        if (authorizeActionsAnnotation != null) {
            for (final AuthorizeAction authorizeActionAnnotation : authorizeActionsAnnotation
                    .actions()) {
                if (!check(action, authorizeActionAnnotation)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean check(final Action action, final AuthorizeAction authorizeActionAnnotation) {

        if (authorizeActionAnnotation != null) {

            if (action.getName().equals(authorizeActionAnnotation.action())) {

                // Roles deniedRoles = new
                // Roles(authorizeActionAnnotation.deny());
                // if (isEmpty(deniedRoles) == false && hasAny(deniedRoles)) {
                // return false;
                // }
                //
                // Roles acceptedRoles = new
                // Roles(authorizeActionAnnotation.roles());
                // if (!(isEmpty(acceptedRoles) || hasAny(acceptedRoles))) {
                // return false;
                // }

            }

        }

        return true;

    }

}
