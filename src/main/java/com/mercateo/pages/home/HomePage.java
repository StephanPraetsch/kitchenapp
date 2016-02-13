package com.mercateo.pages.home;

import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.WicketGuiceHelper;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.pages.general.GeneralPage;

public class HomePage extends GeneralPage {

    private static final long serialVersionUID = 1L;

    public HomePage() {
        add(new Label("welcome", "welcome"));
        add(new UsersListView(WicketGuiceHelper.get().getInstance(UserAccessFactory.class)));
    }

}
