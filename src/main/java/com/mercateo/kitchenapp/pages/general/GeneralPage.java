package com.mercateo.kitchenapp.pages.general;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.kitchenapp.panels.footer.FooterPanel;
import com.mercateo.kitchenapp.panels.header.HeaderPanel;
import com.mercateo.kitchenapp.panels.menu.MenuPanel;

public abstract class GeneralPage extends WebPage {

    public static final String MESSAGE_ID = "message";

    public GeneralPage() {
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
        add(new FooterPanel("footerPanel"));
        add(new Label(MESSAGE_ID, ""));
    }

}
