package com.mercateo.db.mongo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.mercateo.WicketConstants;
import com.mercateo.db.EmailAlreadyExistsExcpetion;
import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAlreadyExistsException;
import com.mercateo.profile.Email;
import com.mercateo.profile.Password;
import com.mercateo.profile.User;
import com.mercateo.profile.Username;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserAccessMongoDb implements UserAccess, Serializable {

    private static final long serialVersionUID = 1L;

    private final DBCollection userCollection;

    public UserAccessMongoDb(MongoDbObjectCreator mongoDbObjectCreator) {
        userCollection = mongoDbObjectCreator.getUserCollection();
    }

    @Override
    public boolean existsUser(User user) {

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

    @Override
    public List<User> listAllUsers() {

        List<User> allUsers = new LinkedList<>();

        DBCursor allUsersDbObjects = userCollection.find();

        for (DBObject userDbObject : allUsersDbObjects) {
            Username username = Username.of((String) userDbObject.get(WicketConstants.USERNAME));
            Password password = Password.of((String) userDbObject.get(WicketConstants.PASSWORD));
            Email email = Email.of((String) userDbObject.get(WicketConstants.EMAIL));
            allUsers.add(User.of(username, password, email));
        }

        return allUsers;

    }

}
