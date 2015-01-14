package com.mercateo.layout;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.mercateo.sso.authorization.AuthenticatedWebSession;

public class HeaderPanel extends Panel {

    public HeaderPanel(String id) {
        super(id);

        if (AuthenticatedWebSession.get().isSignedIn()) {
            add(new Link("logOut") {

                @Override
                public void onClick() {
                    AuthenticatedWebSession.get().invalidate();
                    setResponsePage(getApplication().getHomePage());
                }
            });

        } else {
            add(new Label("logOut"));
        }

    }

}
