package com.mercateo.kitchenapp.models.user;

import java.util.Comparator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.mercateo.kitchenapp.data.User;

public class UserComparator implements Comparator<User> {

    private final SortParam<UserField> sort;

    public UserComparator(SortParam<UserField> sort) {
        this.sort = sort;
    }

    @Override
    public int compare(User u1, User u2) {

        UserField property = sort.getProperty();

        int compare = 0;

        switch (property) {
        case EMAIL:
            compare = u1.getEmail().compareTo(u2.getEmail());
            break;
        default:
            throw new RuntimeException("unknown sort property: " + property);
        }

        if (sort.isAscending()) {
            compare = -compare;
        }

        return compare;

    }

}
