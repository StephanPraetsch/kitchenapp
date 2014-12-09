package com.mercateo.sso.roles.admin;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import com.mercateo.layout.SignInNeededTemplate;

@AuthorizeInstantiation("ADMIN")
public class AdminPage extends SignInNeededTemplate {

}
