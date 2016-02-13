package com.mercateo.pages.editor;

import com.mercateo.pages.general.GenealPageSignInNeeded;
import com.mercateo.sso.authorization.AuthorizeInstantiation;
import com.mercateo.sso.roles.UserRole;

@AuthorizeInstantiation(UserRole.EDITOR)
public class EditorPage extends GenealPageSignInNeeded {

}
