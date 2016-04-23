package com.mercateo.kitchenapp.pages.editor;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.EDITOR)
public class EditorPage extends GeneralPageSignInNeeded {

	public EditorPage(PageParameters params) {
		super(params);
		add(new MealsTable("mealTable", new MealsSortableDataProvider()));
	}

}
