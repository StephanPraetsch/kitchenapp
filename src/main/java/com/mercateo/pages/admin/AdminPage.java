package com.mercateo.pages.admin;

import com.mercateo.pages.general.GenealPageSignInNeeded;
import com.mercateo.sso.authorization.AuthorizeInstantiation;
import com.mercateo.sso.roles.UserRole;

@AuthorizeInstantiation(UserRole.ADMIN)
public class AdminPage extends GenealPageSignInNeeded {

}
