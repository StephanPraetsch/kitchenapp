package com.mercateo.content;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.mercateo.HelloWorld;

public class HelloJava8World extends Panel {

    public HelloJava8World(String id) {
        super(id);
        add(new Label("helloWorldJava8", HelloWorld.helloWorldTomcatJava8()));
    }

}
