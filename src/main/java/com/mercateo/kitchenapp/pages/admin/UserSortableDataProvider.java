package com.mercateo.kitchenapp.pages.admin;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;

public class UserSortableDataProvider extends SortableDataProvider<User, UserField> {

    private final UserAccess userAccess;

    public UserSortableDataProvider(UserAccess userAccess) {
        this.userAccess = userAccess;
        setSort(UserField.EMAIL, SortOrder.ASCENDING);
    }

    @Override
    public Iterator<User> iterator(long first, long count) {

        return userAccess.listAllUsers().stream() //
                .sorted(new UserComparator(getSort())) //
                .skip(first) //
                .limit(count) //
                .iterator();

    }

    @Override
    public long size() {
        return userAccess.listAllUsers().size();
    }

    @Override
    public IModel<User> model(User object) {
        return new UserModel(userAccess, object);
    }

}
