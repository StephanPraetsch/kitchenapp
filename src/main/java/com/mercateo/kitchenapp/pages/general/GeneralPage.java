package com.mercateo.kitchenapp.pages.general;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.kitchenapp.panels.footer.FooterPanel;
import com.mercateo.kitchenapp.panels.header.HeaderPanel;
import com.mercateo.kitchenapp.panels.menu.MenuPanel;
import com.mercateo.kitchenapp.util.WicketConstants;

public abstract class GeneralPage extends WebPage {

    public static final String MESSAGE_ID = "message";

    public GeneralPage(PageParameters params) {
        add(new HeaderPanel("headerPanel"));
        add(new MenuPanel("menuPanel"));
        add(new FooterPanel("footerPanel"));
        StringValue status = params.get(WicketConstants.STATUS);
        add(new Label(MESSAGE_ID, status == null ? "" : status));
    }

}
