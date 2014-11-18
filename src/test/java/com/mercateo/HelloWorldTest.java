package com.mercateo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HelloWorldTest extends TestCase {

    public HelloWorldTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(HelloWorldTest.class);
    }

    public void testApp() {
        assertTrue(true);
    }
}
