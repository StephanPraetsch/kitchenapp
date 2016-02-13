package com.mercateo.kitchenapp.pages.error;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.general.GeneralPage;
import com.mercateo.kitchenapp.util.WicketConstants;

public class ErrorPage extends GeneralPage {

    public ErrorPage(PageParameters params) {
        super(params);
        replace(new Label(MESSAGE_ID, "Ein Fehler ist aufgetreten: " + params.get(
                WicketConstants.STATUS)));
    }

}
