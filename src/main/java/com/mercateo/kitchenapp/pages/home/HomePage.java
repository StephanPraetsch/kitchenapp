package com.mercateo.kitchenapp.pages.home;

import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.pages.general.GeneralPage;

public class HomePage extends GeneralPage {

    private static final long serialVersionUID = 1L;

    public HomePage() {
        add(new Label("welcome", "welcome"));
        add(new UsersListView(WicketGuiceHelper.get().getInstance(UserAccess.class)));
    }

}
