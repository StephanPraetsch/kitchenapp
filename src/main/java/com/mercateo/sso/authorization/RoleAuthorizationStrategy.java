package com.mercateo.sso.authorization;

import org.apache.wicket.authorization.strategies.CompoundAuthorizationStrategy;

public class RoleAuthorizationStrategy extends CompoundAuthorizationStrategy {

    public RoleAuthorizationStrategy(final IRoleCheckingStrategy roleCheckingStrategy) {

        add(new AnnotationsRoleAuthorizationStrategy(roleCheckingStrategy));
//        add(new MetaDataRoleAuthorizationStrategy(roleCheckingStrategy));

    }

}
