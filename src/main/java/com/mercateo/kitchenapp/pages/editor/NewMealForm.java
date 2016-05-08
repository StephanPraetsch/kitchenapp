package com.mercateo.kitchenapp.pages.editor;

import java.util.Collections;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mercateo.kitchenapp.data.Chip;
import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.data.Price;
import com.mercateo.kitchenapp.db.AlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.Meals;

public class NewMealForm extends Form<Meal> {

    private static final Logger logger = LoggerFactory.getLogger(NewMealForm.class);

    private final TextField<String> title;

    private final TextField<String> description;

    private final TextField<String> prices;

    private final Meals meals;

    public NewMealForm(String id, Meals meals) {
        super(id);
        this.meals = meals;

        title = new TextField<>("title", Model.of(""));
        description = new TextField<>("description", Model.of(""));
        prices = new TextField<>("prices", Model.of(""));

        add(title);
        add(description);
        add(prices);

    }

    @Override
    public void onSubmit() {

        String c = prices.getModelObject();

        Meal meal = Meal.builder() //
                .title(title.getModelObject()) //
                .description(description.getModelObject()) //
                .prices(Collections.singleton(new Price(Chip.builder().title(c).price(1.0f)
                        .build()))) //
                .build();

        logger.info("adding new meal " + meal);
        try {
            meals.addMeal(meal);
        } catch (AlreadyExistsExcpetion e) {
            logger.error("could not add new meal: " + meal, e);
        }

    }

}
