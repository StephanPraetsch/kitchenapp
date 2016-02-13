package com.mercateo.pages.denied;

import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.layout.HeaderMiddleFooterTemplate;

public class AccessDeniedPage extends HeaderMiddleFooterTemplate {

    public AccessDeniedPage() {
        replace(new Label(MESSAGE_ID, "access denied"));
    }

}
