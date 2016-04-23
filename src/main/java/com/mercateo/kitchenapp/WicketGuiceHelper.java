package com.mercateo.kitchenapp;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import org.apache.wicket.Application;

import com.google.inject.Injector;

public class WicketGuiceHelper {

	private static Injector inj;

	static void set(Injector inj) {
		WicketGuiceHelper.inj = inj;
	}

	public static Injector get() {
		return inj;
	}

	public static void injectMembers(final Object o) {
		Application application = Application.get();
		checkState(application != null, "Application is not attached");
		org.apache.wicket.injection.Injector injector = org.apache.wicket.injection.Injector.get();
		injector.inject(checkNotNull(o));
	}
}
