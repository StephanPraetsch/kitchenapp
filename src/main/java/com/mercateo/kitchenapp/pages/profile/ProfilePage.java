package com.mercateo.kitchenapp.pages.profile;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.UserWebSession;
import com.mercateo.kitchenapp.util.WicketConstants;

public class ProfilePage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    public ProfilePage(PageParameters params) {
        super(params);
    }

    @Override
    protected void onBeforeRender() {

        super.onBeforeRender();

        UserWebSession session = UserWebSession.get();

        User user = session.getUser();

        add(new Label(WicketConstants.EMAIL, user.getEmail().asString()));
        add(new Label(WicketConstants.PASSWORD, user.getPassword().asString()));

    }

}
