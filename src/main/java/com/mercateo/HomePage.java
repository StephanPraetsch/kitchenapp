package com.mercateo;

import java.net.UnknownHostException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.mongo.MongoDbAccess;
import com.mercateo.sso.LoginForm;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) throws UnknownHostException {
        super(parameters);

        StringValue stringValue = parameters.get("msg");
        if (stringValue != null) {
            add(new Label("msg", stringValue));
        }

        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

        add(new Label("helloWorldJava8", HelloWorld.helloWorldTomcatJava8()));

        add(new Label("helloWorldMongo", new MongoDbAccess().sayHello()));

        add(new LoginForm("loginForm"));

    }

}
