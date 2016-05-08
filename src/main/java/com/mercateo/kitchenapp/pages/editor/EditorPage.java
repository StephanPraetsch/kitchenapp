package com.mercateo.kitchenapp.pages.editor;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.inject.Provider;
import com.mercateo.kitchenapp.db.Meals;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.EDITOR)
public class EditorPage extends GeneralPageSignInNeeded {

    @Inject
    private Provider<Meals> meals;

    public EditorPage(PageParameters params) {
        super(params);
        add(new NewMealForm("newMeal", meals.get()));
        add(new MealsTable("mealTable", new MealsSortableDataProvider(meals.get())));
    }

}
