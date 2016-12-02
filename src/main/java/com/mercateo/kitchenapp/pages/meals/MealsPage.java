package com.mercateo.kitchenapp.pages.meals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.kitchenapp.db.MealsDao;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.EDITOR)
public class MealsPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    @Inject
    private MealsDao meals;

    private final LocalDate day;

    public MealsPage(PageParameters params) {
        super(params);
        this.day = day(params);
        add(new AssignMealForm("assignForm", Model.of(day)));
        add(new NewMealForm("newMeal"));
        add(new MealsTable("mealTable", meals));
    }

    private LocalDate day(PageParameters params) {
        return readParams(params, "day", LocalDate.now());
    }

    private LocalDate readParams(PageParameters params, String name, LocalDate defaultValue) {
        StringValue stringValue = params.get(name);
        if (stringValue.isEmpty()) {
            return defaultValue;
        }
        return LocalDate.parse(stringValue.toString(), FORMATTER);
    }

}
