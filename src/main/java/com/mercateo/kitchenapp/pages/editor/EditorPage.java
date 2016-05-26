package com.mercateo.kitchenapp.pages.editor;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.db.MealsDao;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.EDITOR)
public class EditorPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    @Inject
    private MealsDao meals;

    public EditorPage(PageParameters params) {
        super(params);
        add(new NewMealForm("newMeal"));
        add(new MealsTable("mealTable", meals));
    }

}
