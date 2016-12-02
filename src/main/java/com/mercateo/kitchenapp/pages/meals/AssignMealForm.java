package com.mercateo.kitchenapp.pages.meals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.MealsDao;
import com.mercateo.kitchenapp.db.OffersDao;
import com.mercateo.kitchenapp.pages.PagesRegistry;

public class AssignMealForm extends Form<LocalDate> {

    private static final long serialVersionUID = 1L;

    @Inject
    private PagesRegistry pagesRegistry;

    @Inject
    private MealsDao meals;

    @Inject
    private OffersDao offers;

    private final DateField dayField;

    private final TextField<String> textField;

    public AssignMealForm(String id, IModel<LocalDate> model) {

        super(id, model);

        this.dayField = new DateField("filterDay", Model.of(convert(model.getObject())));
        this.textField = new TextField<>("filterMeal", Model.of(""));

        add(dayField);
        add(textField);

    }

    @Override
    protected void onSubmit() {
        super.onSubmit();

        PageParameters pageParameters = new PageParameters();
        pageParameters.add("day", convert(dayField));
        setResponsePage(pagesRegistry.getMealsPage(), pageParameters);

        String content = textField.getModelObject();
        Optional<Meal> meal = meals.get(content);
        if (meal.isPresent()) {
            LocalDate day = convert(dayField.getModelObject());
            offers.add(day, meal.get());
        }

    }

    private Date convert(LocalDate ld) {
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private String convert(DateField field) {
        Date date = field.getModel().getObject();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return MealsPage.FORMATTER.format(localDate);
    }

    private LocalDate convert(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
