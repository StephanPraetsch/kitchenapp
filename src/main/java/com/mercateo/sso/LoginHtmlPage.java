package com.mercateo.sso;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LoginHtmlPage extends WebPage {

    private static final long serialVersionUID = 1L;

    public LoginHtmlPage(final PageParameters parameters) {
        super(parameters);

        add(new LoginForm("loginForm"));

    }

}
