package com.mercateo.pages.profile;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.pages.general.GenealPageSignInNeeded;
import com.mercateo.util.WicketConstants;

public class ProfilePage extends GenealPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    public ProfilePage(PageParameters parameters) {

        StringValue email = parameters.get(WicketConstants.EMAIL);
        if (email != null) {
            add(new Label(WicketConstants.EMAIL, email));
        }

        StringValue password = parameters.get(WicketConstants.PASSWORD);
        if (password != null) {
            add(new Label(WicketConstants.PASSWORD, password));
        }

    }

}
