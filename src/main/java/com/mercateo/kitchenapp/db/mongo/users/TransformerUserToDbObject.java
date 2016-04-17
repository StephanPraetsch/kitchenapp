package com.mercateo.kitchenapp.db.mongo.users;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.sso.roles.UserRole;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TransformerUserToDbObject implements Function<User, DBObject> {

    @Override
    public DBObject apply(User user) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbUserConstants.EMAIL, getEmail(user));
                getPassword(user).ifPresent(pw -> put(MongoDbUserConstants.PASSWORD, pw));
                getUserRoles(user).ifPresent(roles -> put(MongoDbUserConstants.USER_ROLES, roles));
            }

        });

        return dbUserObject;

    }

    private String getEmail(User user) {
        return user.getEmail().asString();
    }

    private Optional<String> getPassword(User user) {
        return Optional.ofNullable(user.getPassword()).map(Password::asString);
    }

    private Optional<BasicDBList> getUserRoles(User user) {
        Set<UserRole> roles = user.getUserRoles();
        if (roles == null) {
            return Optional.empty();
        }
        BasicDBList list = new BasicDBList();
        user.getUserRoles().stream() //
                .map(String::valueOf) //
                .forEach(list::add);
        return Optional.of(list);
    }

}
