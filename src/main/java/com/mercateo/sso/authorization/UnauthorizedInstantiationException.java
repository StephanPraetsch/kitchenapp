package com.mercateo.sso.authorization;

import org.apache.wicket.authorization.AuthorizationException;
import org.apache.wicket.core.util.lang.WicketObjects;
import org.apache.wicket.request.component.IRequestableComponent;

public class UnauthorizedInstantiationException extends AuthorizationException {

    private static final long serialVersionUID = 1L;

    private final String componentClassName;

    public <T extends IRequestableComponent> UnauthorizedInstantiationException(
            final Class<T> componentClass) {
        super("Not authorized to instantiate class " + componentClass.getName());
        componentClassName = componentClass.getName();
    }

    public Class<? extends IRequestableComponent> getComponentClass() {
        return WicketObjects.resolveClass(componentClassName);
    }
}
