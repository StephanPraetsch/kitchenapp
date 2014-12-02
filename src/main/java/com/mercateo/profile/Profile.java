package com.mercateo.profile;

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.WicketConstants;
import com.mercateo.layout.HeaderMiddleFooterTemplate;

public class Profile extends HeaderMiddleFooterTemplate {

    private static final long serialVersionUID = 1L;

    public Profile(PageParameters parameters) {

        StringValue username = parameters.get(WicketConstants.USERNAME);
        if (username != null) {
            add(new Label(WicketConstants.USERNAME, username));
        }

        StringValue password = parameters.get(WicketConstants.PASSWORD);
        if (password != null) {
            add(new Label(WicketConstants.PASSWORD, password));
        }

    }

    @Override
    protected void onConfigure() {

        AuthenticatedWebApplication app = (AuthenticatedWebApplication) AuthenticatedWebApplication
                .get();

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            app.restartResponseAtSignInPage();
        }

    }

}
