package com.mercateo.kitchenapp.pages.profile;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.util.WicketConstants;

public class ProfilePage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    public ProfilePage(PageParameters params) {
        super(params);

        StringValue email = params.get(WicketConstants.EMAIL);
        if (email != null) {
            add(new Label(WicketConstants.EMAIL, email));
        }

        StringValue password = params.get(WicketConstants.PASSWORD);
        if (password != null) {
            add(new Label(WicketConstants.PASSWORD, password));
        }

    }

}
