package com.mercateo.kitchenapp.pages.admin;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.util.WicketConstants;

class UsersListView extends RepeatingView {

    UsersListView(UserAccess userAccess) {
        super("usersList");
        add(createView(userAccess));
    }

    private RepeatingView createView(UserAccess userAccess) {

        List<User> allUsers = userAccess.listAllUsers();

        DataView<User> dataView = new DataView<User>("repeating", new ListDataProvider<>(
                allUsers)) {
            @Override
            public void populateItem(Item<User> item) {
                User user = item.getModelObject();
                item.add(new Label(WicketConstants.EMAIL, user.getEmail().asString()));
                item.add(new Label(WicketConstants.PASSWORD, user.getPassword().asString()));
                item.add(new Label(WicketConstants.ROLES, user.getUserRoles().toString()));
            }
        };

        dataView.setItemsPerPage(10);

        return dataView;

    }

}
