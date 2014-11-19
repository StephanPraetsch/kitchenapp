package com.mercateo;

import java.net.UnknownHostException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.mongo.MongoDbAccess;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) throws UnknownHostException {
        super(parameters);

        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

        add(new Label("helloWorldJava8", HelloWorld.helloWorldTomcatJava8()));

        add(new Label("helloWorldMongo", new MongoDbAccess().sayHello()));

    }

}
