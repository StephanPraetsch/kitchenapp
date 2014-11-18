package com.mercateo;

import java.util.Arrays;
import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {

        String[] helloWorld = { "", "Hello", " ", "", "World", "!" };

        List<String> list = Arrays.asList(helloWorld);

        list.stream().filter(s -> !s.isEmpty()).forEach(s -> System.out.print(s));

    }

}
