package com.mercateo.pages.admin;

import com.mercateo.layout.SignInNeededTemplate;
import com.mercateo.sso.authorization.AuthorizeInstantiation;
import com.mercateo.sso.roles.UserRole;

@AuthorizeInstantiation(UserRole.ADMIN)
public class AdminPage extends SignInNeededTemplate {

}
