package com.mercateo.pages.denied;

import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.pages.general.GeneralPage;

public class AccessDeniedPage extends GeneralPage {

    public AccessDeniedPage() {
        replace(new Label(MESSAGE_ID, "access denied"));
    }

}
