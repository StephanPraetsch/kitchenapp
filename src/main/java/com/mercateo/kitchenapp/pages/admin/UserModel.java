package com.mercateo.kitchenapp.pages.admin;

import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;
import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;

public class UserModel extends LoadableDetachableModel<User> {

	@Inject
	private UserAccess userAccess;

	private final Email email;

	public UserModel(User user) {
		super(user);
		WicketGuiceHelper.injectMembers(this);
		this.email = user.getEmail();
	}

	@Override
	protected User load() {
		return userAccess.get(email) //
				.orElseThrow(() -> new RuntimeException("missing user: " + email));
	}

}
