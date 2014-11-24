package com.mercateo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.db.UserAccessFactory;
import com.mercateo.db.mongo.MongoDbConfiguration;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.sso.LoginForm;
import com.mercateo.sso.RegisterForm;
import com.mercateo.sso.UsersListView;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        StringValue stringValue = parameters.get(WicketConstants.STATUS);
        if (stringValue != null) {
            add(new Label(WicketConstants.STATUS, stringValue));
        }

        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

        add(new Label("helloWorldJava8", HelloWorld.helloWorldTomcatJava8()));

        UserAccessFactory userAccessFactory = new UserAccessFactoryForMongoDb(
                new MongoDbConfiguration());

        add(new LoginForm("loginForm", userAccessFactory));

        add(new RegisterForm("registerForm", userAccessFactory));

        add(new UsersListView("usersList", userAccessFactory));

    }

}
