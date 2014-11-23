package com.mercateo;

import java.net.UnknownHostException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.db.mongo.MongoDbAccess;
import com.mercateo.db.mongo.MongoDbConfiguration;
import com.mercateo.db.mongo.UserAccessFactory;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.sso.LoginForm;
import com.mercateo.sso.RegisterForm;

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

        UserAccessFactory userAccessFactory = new UserAccessFactoryForMongoDb(
                new MongoDbConfiguration());

        add(new LoginForm("loginForm", userAccessFactory));

        add(new RegisterForm("registerForm", userAccessFactory));

    }

}
