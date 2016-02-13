package com.mercateo.kitchenapp.pages.admin;

import com.mercateo.kitchenapp.pages.general.GenealPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.AuthorizeInstantiation;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@AuthorizeInstantiation(UserRole.ADMIN)
public class AdminPage extends GenealPageSignInNeeded {

}
