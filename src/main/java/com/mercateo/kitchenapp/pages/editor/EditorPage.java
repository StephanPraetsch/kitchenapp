package com.mercateo.kitchenapp.pages.editor;

import com.mercateo.kitchenapp.pages.general.GenealPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.AuthorizeInstantiation;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@AuthorizeInstantiation(UserRole.EDITOR)
public class EditorPage extends GenealPageSignInNeeded {

}
