package com.mercateo.sso;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;

import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.profile.User;
import com.mercateo.util.WicketConstants;

public class UsersListView extends RepeatingView {

    // TODO transient logger?
    private transient final Logger logger = Logger.getLogger(UsersListView.class);

    public UsersListView(String id, UserAccessFactory userAccessFactory) {
        super(id);
        listAllUsers(userAccessFactory);
    }

    private void listAllUsers(UserAccessFactory userAccessFactory) {

        try {

            UserAccess userAccess = userAccessFactory.create();

            List<User> allUsers = userAccess.listAllUsers();

            RepeatingView repeating = new RepeatingView("repeating");
            add(repeating);

            int index = 0;
            for (User user : allUsers) {

                AbstractItem item = new AbstractItem(repeating.newChildId());

                repeating.add(item);

                item.add(new Label(WicketConstants.EMAIL, user.getEmail().asString()));
                item.add(new Label(WicketConstants.PASSWORD, user.getPassword().asString()));
                item.add(new Label("userRoles", user.getUserRoles().toString()));

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

        } catch (UserAccessCreationException e) {
            logger.error("internal error: '" + e.getMessage() + "'");
        }

    }

}
