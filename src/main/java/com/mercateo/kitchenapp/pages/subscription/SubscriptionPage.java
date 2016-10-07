package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDateTime;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;

public class SubscriptionPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    public SubscriptionPage(PageParameters params) {
        super(params);
        add(new Label("date", new Model<>(LocalDateTime.now())));
    }

}
