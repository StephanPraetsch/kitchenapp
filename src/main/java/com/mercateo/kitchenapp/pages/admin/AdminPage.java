package com.mercateo.kitchenapp.pages.admin;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.ADMIN)
public class AdminPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserAccess userAccess;

    public AdminPage(PageParameters params) {
        super(params);
        add(new UserTable("userTable", new UserSortableDataProvider(userAccess)));
    }

}
