package com.mercateo.kitchenapp.pages.meals;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.db.MealsDao;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.EDITOR)
public class MealsPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    @Inject
    private MealsDao meals;

    public MealsPage(PageParameters params) {
        super(params);
    }

    @Override
    protected void onBeforeRender() {

        super.onBeforeRender();

        add(new NewMealForm("newMeal"));
        add(new MealsTable("mealTable", meals));

    }

}
