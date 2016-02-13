package com.mercateo;

import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.db.UserAccessFactory;
import com.mercateo.layout.HeaderMiddleFooterTemplate;
import com.mercateo.sso.UsersListView;

public class HomePage extends HeaderMiddleFooterTemplate {

    private static final long serialVersionUID = 1L;

    public HomePage() {
        add(new Label("welcome", "welcome"));
        add(new UsersListView("usersList", WicketGuiceHelper.get().getInstance(
                UserAccessFactory.class)));
    }

}
