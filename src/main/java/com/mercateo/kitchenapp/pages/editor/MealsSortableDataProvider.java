package com.mercateo.kitchenapp.pages.editor;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import com.google.inject.Inject;
import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.data.Meal;
import com.mercateo.kitchenapp.db.Meals;

public class MealsSortableDataProvider extends SortableDataProvider<Meal, MealField> {

	@Inject
	private Meals meals;

	public MealsSortableDataProvider() {
		WicketGuiceHelper.injectMembers(this);
		setSort(MealField.TITLE, SortOrder.ASCENDING);
	}

	@Override
	public Iterator<Meal> iterator(long first, long count) {

		return meals.get() //
				.sorted(new MealComparator(getSort())) //
				.skip(first) //
				.limit(count) //
				.iterator();

	}

	@Override
	public long size() {
		return meals.get().count();
	}

	@Override
	public IModel<Meal> model(Meal m) {
		return new MealModel(m);
	}

}
