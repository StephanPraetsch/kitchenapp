package com.mercateo;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

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
