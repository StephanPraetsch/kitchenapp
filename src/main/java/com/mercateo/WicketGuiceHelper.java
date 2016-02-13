package com.mercateo;

import com.google.inject.Injector;

public class WicketGuiceHelper {

    private static Injector inj;

    static void set(Injector inj) {
        WicketGuiceHelper.inj = inj;
    }

    public static Injector get() {
        return inj;
    }

}
