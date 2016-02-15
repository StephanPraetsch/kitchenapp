package com.mercateo.kitchenapp.pages.home;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.util.WicketConstants;

class UsersListView extends RepeatingView {

    UsersListView(UserAccess userAccess) {
        super("usersList");
        add(createView(userAccess));
    }

    private RepeatingView createView(UserAccess userAccess) {

        RepeatingView repeating = new RepeatingView("repeating");

        List<User> allUsers = userAccess.listAllUsers();

        int index = 0;
        for (User user : allUsers) {

            AbstractItem item = new AbstractItem(repeating.newChildId());

            repeating.add(item);

            item.add(new Label(WicketConstants.EMAIL, user.getEmail().asString()));
            item.add(new Label(WicketConstants.PASSWORD, user.getPassword().asString()));
            item.add(new Label(WicketConstants.ROLES, user.getUserRoles().toString()));

            final int idx = index;
            item.add(AttributeModifier.replace("class", new AbstractReadOnlyModel<String>() {
                private static final long serialVersionUID = 1L;

                @Override
                public String getObject() {
                    return (idx % 2 == 1) ? "even" : "odd";
                }
            }));

            index++;
        }

        return repeating;

    }

}
