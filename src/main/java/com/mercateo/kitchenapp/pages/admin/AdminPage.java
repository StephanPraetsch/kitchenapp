package com.mercateo.kitchenapp.pages.admin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.ADMIN)
public class AdminPage extends GeneralPageSignInNeeded {

    public AdminPage(PageParameters params) {
        super(params);
    }

}
