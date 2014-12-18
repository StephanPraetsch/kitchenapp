package com.mercateo.sso.roles.admin;

import com.mercateo.layout.SignInNeededTemplate;
import com.mercateo.sso.authorization.AuthorizeInstantiation;
import com.mercateo.sso.authorization.UserRole;

@AuthorizeInstantiation(UserRole.ADMIN)
public class AdminPage extends SignInNeededTemplate {

}
