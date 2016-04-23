package com.mercateo.kitchenapp.pages.editor;

import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;
import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.Meals;

public class MealModel extends LoadableDetachableModel<Meal> {

	@Inject
	private Meals meals;

	private final String title;

	public MealModel(Meal m) {
		super(m);
		WicketGuiceHelper.injectMembers(this);
		this.title = m.getTitle();
	}

	@Override
	protected Meal load() {
		return meals.get(title) //
				.orElseThrow(() -> new RuntimeException("missing user: " + title));
	}

}
