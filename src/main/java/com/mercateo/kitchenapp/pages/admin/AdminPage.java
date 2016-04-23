package com.mercateo.kitchenapp.pages.admin;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Provider;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.ADMIN)
public class AdminPage extends GeneralPageSignInNeeded {

    public AdminPage(PageParameters params) {
        super(params);
        add(new UserTable("userTable", new UserSortableDataProvider()));
    }

}
