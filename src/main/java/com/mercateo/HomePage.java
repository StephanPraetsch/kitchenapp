package com.mercateo;

import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.layout.HeaderMiddleFooterTemplate;

public class HomePage extends HeaderMiddleFooterTemplate {

    private static final long serialVersionUID = 1L;

    public HomePage() {
        add(new Label("welcome", "welcome"));
    }

}
