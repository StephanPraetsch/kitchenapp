package com.mercateo.kitchenapp.pages.general;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

class FooterPanel extends Panel {

    FooterPanel(String id) {
        super(id);

        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

    }

}
