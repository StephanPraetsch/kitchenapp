package com.mercateo.kitchenapp.pages.admin;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.ADMIN)
public class AdminPage extends GeneralPageSignInNeeded {

    public AdminPage(PageParameters params) {
        super(params);
        UserAccess ua = WicketGuiceHelper.get().getInstance(UserAccess.class);
        add(new UserTable("userTable", new UserSortableDataProvider(ua)));
    }

}
