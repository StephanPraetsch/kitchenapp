package com.mercateo.profile;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class Profile extends WebPage {

    private static final long serialVersionUID = 1L;

    public Profile(final PageParameters parameters) {
        super(parameters);

        StringValue username = parameters.get("username");
        if (username != null) {
            add(new Label("username", username));
        }

        StringValue password = parameters.get("password");
        if (password != null) {
            add(new Label("password", password));
        }

    }

}
