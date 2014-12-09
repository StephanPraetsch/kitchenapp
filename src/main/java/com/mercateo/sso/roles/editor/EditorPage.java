package com.mercateo.sso.roles.editor;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import com.mercateo.layout.SignInNeededTemplate;

@AuthorizeInstantiation("SIGNED_IN")
public class EditorPage extends SignInNeededTemplate {

}
