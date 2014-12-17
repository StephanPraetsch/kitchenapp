package com.mercateo.sso.roles.editor;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import com.mercateo.layout.SignInNeededTemplate;
import com.mercateo.sso.UserRole;

@AuthorizeInstantiation(UserRole.EDITOR)
public class EditorPage extends SignInNeededTemplate {

}
