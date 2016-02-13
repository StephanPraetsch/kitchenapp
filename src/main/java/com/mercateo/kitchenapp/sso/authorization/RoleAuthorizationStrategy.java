package com.mercateo.kitchenapp.sso.authorization;

import org.apache.wicket.authorization.strategies.CompoundAuthorizationStrategy;

import com.mercateo.kitchenapp.sso.roles.RoleCheckingStrategy;

public class RoleAuthorizationStrategy extends CompoundAuthorizationStrategy {

    public RoleAuthorizationStrategy(final RoleCheckingStrategy roleCheckingStrategy) {

        add(new AnnotationsRoleAuthorizationStrategy(roleCheckingStrategy));
//        add(new MetaDataRoleAuthorizationStrategy(roleCheckingStrategy));

    }

}
