package com.mercateo.db.mongo;

import java.io.Serializable;
import java.util.HashMap;

import com.mercateo.sso.User;
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
        BasicDBObject username = new BasicDBObject("username", user.getUsername().asString());
        DBCursor usernames = userCollection.find(username);
        return usernames.size() > 0;
    }

    private boolean existsEmail(User user) {
        BasicDBObject email = new BasicDBObject("email", user.getEmail().asString());
        DBCursor emails = userCollection.find(email);
        return emails.size() > 0;
    }

    @Override
    public void addUser(final User user) throws UserAlreadyExistsException {

        if (userExists(user)) {
            throw new UserAlreadyExistsException(user);
        }

        BasicDBObject newDbUser = new BasicDBObject(new HashMap<String, String>() {
            {
                put("username", user.getUsername().asString());
                put("password", user.getPassword().asString());
                put("email", user.getEmail().asString());
            }
        });

        userCollection.insert(newDbUser);

    }

}
