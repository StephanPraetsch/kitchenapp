package com.mercateo.db.mongo;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import com.mercateo.data.Password;
import com.mercateo.data.User;
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
                // put(MongoDbConstants.USER_ROLES, getUserRoles(user));
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

    private BasicDBList getUserRoles(User user) {
        BasicDBList list = new BasicDBList();
        user.getUserRoles().forEach((role) -> list.add(String.valueOf(role)));
        return list;
    }

}
