package com.mercateo.db.mongo;

import java.util.function.Function;

import com.mercateo.profile.Email;
import com.mercateo.profile.Password;
import com.mercateo.profile.User;
import com.mongodb.DBObject;

public class TransformerDbObjectToUser implements Function<DBObject, User> {

    @Override
    public User apply(DBObject userDbObject) {

        Email email = Email.of((String) userDbObject.get(MongoDbConstants.EMAIL));

        Password password = Password.of((String) userDbObject.get(MongoDbConstants.PASSWORD));

        User user = User.of(email, password);

        return user;

    }

}
