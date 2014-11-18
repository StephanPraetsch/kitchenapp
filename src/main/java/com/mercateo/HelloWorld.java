package com.mercateo;

import java.util.Arrays;
import java.util.List;

public class HelloWorld {

    public static String helloWorldTomcatJava8() {

        String[] helloWorld = { "", "Hello", " ", "", "World", " Tomcat", " Java8 ", "!" };

        List<String> list = Arrays.asList(helloWorld);

        StringBuilder sb = new StringBuilder();

        list.stream().filter(s -> !s.isEmpty()).forEach(s -> sb.append(s));

        return sb.toString();

    }

}
