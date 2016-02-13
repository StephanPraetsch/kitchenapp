package com.mercateo.pages.general;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.panels.footer.FooterPanel;
import com.mercateo.panels.header.HeaderPanel;
import com.mercateo.panels.menu.MenuPanel;

public abstract class GeneralPage extends WebPage {

    public static final String MESSAGE_ID = "message";

    public GeneralPage() {
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
        add(new FooterPanel("footerPanel"));
        add(new Label(MESSAGE_ID, ""));
    }

}
