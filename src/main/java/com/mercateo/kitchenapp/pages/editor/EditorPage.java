package com.mercateo.kitchenapp.pages.editor;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.AuthorizeInstantiation;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@AuthorizeInstantiation(UserRole.EDITOR)
public class EditorPage extends GeneralPageSignInNeeded {

    public EditorPage(PageParameters params) {
        super(params);
    }

}
