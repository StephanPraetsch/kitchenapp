package com.mercateo.kitchenapp.db.mongo;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.sso.roles.UserRole;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class TransformerDbObjectToUser implements Function<DBObject, User> {

    @Override
    public User apply(DBObject userDbObject) {

        Email email = getEmail(userDbObject);

        Password password = getPassword(userDbObject);

        Set<UserRole> userRoles = getUserRoles(userDbObject);

        User user = User.builder().email(email).password(password).userRoles(userRoles).build();

        return user;

    }

    private Email getEmail(DBObject userDbObject) {
        return Email.of((String) userDbObject.get(MongoDbConstants.EMAIL));
    }

    private Password getPassword(DBObject userDbObject) {
        return Password.of((String) userDbObject.get(MongoDbConstants.PASSWORD));
    }

    private Set<UserRole> getUserRoles(DBObject userDbObject) {

        Set<UserRole> userRoles = new HashSet<UserRole>();

        Object object = userDbObject.get(MongoDbConstants.USER_ROLES);
        if (object instanceof BasicDBList) {
            BasicDBList list = (BasicDBList) object;
            list.forEach((o) -> userRoles.add(UserRole.valueOf(String.valueOf(o))));
        }

        return userRoles;

    }
}
