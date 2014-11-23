package com.mercateo.db.mongo;

import java.io.Serializable;
import java.util.HashMap;

import com.mercateo.WicketConstants;
import com.mercateo.sso.Email;
import com.mercateo.sso.User;
import com.mercateo.sso.Username;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class UserAccessMongoDb implements UserAccess, Serializable {

    private static final long serialVersionUID = 1L;

    private final DBCollection userCollection;

    public UserAccessMongoDb(MongoDbObjectCreator mongoDbObjectCreator) {
        userCollection = mongoDbObjectCreator.getUserCollection();
    }

    @Override
    public boolean userExists(User user) {

        boolean usernameExists = existsUsername(user);

        boolean emailExists = existsEmail(user);

        return usernameExists || emailExists;

    }

    private boolean existsUsername(User user) {
        Username username = user.getUsername();
        if (username == null) {
            return false;
        }
        BasicDBObject usernameDbObject = new BasicDBObject(WicketConstants.USERNAME, username
                .asString());
        DBCursor usernames = userCollection.find(usernameDbObject);
        return usernames.size() > 0;
    }

    private boolean existsEmail(User user) {
        Email email = user.getEmail();
        if (email == null) {
            return false;
        }
        BasicDBObject emailDbObject = new BasicDBObject(WicketConstants.EMAIL, email.asString());
        DBCursor emails = userCollection.find(emailDbObject);
        return emails.size() > 0;
    }

    @Override
    public void addUser(final User user) throws UserAlreadyExistsException,
            EmailAlreadyExistsExcpetion {

        if (existsUsername(user)) {
            throw new UserAlreadyExistsException(user);
        }

        if (existsEmail(user)) {
            throw new EmailAlreadyExistsExcpetion(user);
        }

        BasicDBObject newDbUser = new BasicDBObject(new HashMap<String, String>() {
            {
                put(WicketConstants.USERNAME, user.getUsername().asString());
                put(WicketConstants.PASSWORD, user.getPassword().asString());
                put(WicketConstants.EMAIL, user.getEmail().asString());
            }
        });

        userCollection.insert(newDbUser);

    }

}
