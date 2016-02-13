package com.mercateo.kitchenapp.db.mongo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserCollection {

    private final DBCollection userCollection;

    @Inject
    UserCollection(DBCollection userCollection) {
        this.userCollection = userCollection;
    }

    Optional<DBObject> findOne(DBObject dbObject) throws DuplicateFoundException {

        DBCursor cursor = userCollection.find(dbObject);

        if (cursor.size() == 0) {
            return Optional.empty();
        }

        if (cursor.size() > 1) {
            throw new DuplicateFoundException(dbObject, cursor);
        }

        DBObject foundUserDbObject = cursor.next();

        return Optional.of(foundUserDbObject);

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
