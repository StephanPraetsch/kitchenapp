package com.mercateo.db.mongo;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.mercateo.data.Password;
import com.mercateo.data.User;
import com.mercateo.sso.roles.UserRole;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TransformerUserToDbObject implements Function<User, DBObject> {

    @Override
    public DBObject apply(User user) {

        BasicDBObject dbUserObject = new BasicDBObject(new HashMap<String, Object>() {
            {
                put(MongoDbConstants.EMAIL, getEmail(user));
                getPassword(user).ifPresent(pw -> put(MongoDbConstants.PASSWORD, pw));
                getUserRoles(user).ifPresent(roles -> put(MongoDbConstants.USER_ROLES, roles));
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
        if (roles.isEmpty()) {
            return Optional.empty();
        }
        BasicDBList list = new BasicDBList();
        user.getUserRoles().stream() //
                .map(String::valueOf) //
                .forEach(list::add);
        return Optional.of(list);
    }

}
