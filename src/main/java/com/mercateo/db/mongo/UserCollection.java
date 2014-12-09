package com.mercateo.db.mongo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.mercateo.profile.Email;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserCollection {

    private static final Logger logger = Logger.getLogger(UserAccessMongoDb.class);

    private final DBCollection userCollection;

    UserCollection(DBCollection userCollection) {
        this.userCollection = userCollection;
    }

    Optional<DBObject> getUser(Email email) {

        BasicDBObject emailDbObject = new BasicDBObject(MongoDbConstants.EMAIL, email.asString());

        DBCursor emailCursor = userCollection.find(emailDbObject);

        if (emailCursor.size() == 0) {
            return Optional.empty();
        }

        if (emailCursor.size() > 1) {
            logger.error("duplicate email found: " + email);
        }

        DBObject userDbObject = emailCursor.next();

        return Optional.of(userDbObject);

    }

    public void insert(DBObject newDbUserObject) {
        userCollection.insert(newDbUserObject);
    }

    public List<DBObject> findAllUsers() {

        List<DBObject> allUsers = new LinkedList<>();

        DBCursor allUsersDbObjects = userCollection.find();

        for (DBObject userDbObject : allUsersDbObjects) {
            allUsers.add(userDbObject);
        }

        return allUsers;

    }

}
