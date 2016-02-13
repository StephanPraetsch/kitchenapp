package com.mercateo.kitchenapp.pages.denied;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.general.GeneralPage;

public class AccessDeniedPage extends GeneralPage {

    public AccessDeniedPage(PageParameters params) {
        super(params);
        replace(new Label(MESSAGE_ID, "access denied"));
    }

}
