package com.mercateo.sso.roles.editor;

import com.mercateo.layout.SignInNeededTemplate;
import com.mercateo.sso.authorization.AuthorizeInstantiation;
import com.mercateo.sso.roles.UserRole;

@AuthorizeInstantiation(UserRole.EDITOR)
public class EditorPage extends SignInNeededTemplate {

}
