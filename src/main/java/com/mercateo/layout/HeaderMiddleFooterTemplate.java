package com.mercateo.layout;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import com.mercateo.HeaderPanel;

public class HeaderMiddleFooterTemplate extends WebPage {

    public static final String MESSAGE_ID = "message";

    private Component headerPanel;

    private Component menuPanel;

    private Component footerPanel;

    public HeaderMiddleFooterTemplate() {
        add(headerPanel = new HeaderPanel("headerPanel"));
        add(menuPanel = new MenuPanel("menuPanel"));
        add(footerPanel = new FooterPanel("footerPanel"));
        add(new Label(MESSAGE_ID, ""));
    }

    protected Component getMenuPanel() {
        return menuPanel;
    }

}
