package com.mercateo.kitchenapp.db.mongo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDbCollection {

    private final DBCollection collection;

    protected MongoDbCollection(DBCollection userCollection) {
        this.collection = userCollection;
    }

    public Optional<DBObject> findOne(DBObject dbObject) throws DuplicateFoundException {

        DBCursor cursor = collection.find(dbObject);

        if (cursor.size() == 0) {
            return Optional.empty();
        }

        if (cursor.size() > 1) {
            throw new DuplicateFoundException(dbObject, cursor);
        }

        DBObject foundDbObject = cursor.next();

        return Optional.of(foundDbObject);

    }

    public void insert(DBObject newDbObject) {
        collection.insert(newDbObject);
    }

    public void update(DBObject updateDbObject) {
        collection.update(updateDbObject, updateDbObject);
    }

    public List<DBObject> find(DBObject dbObject) {
        DBCursor find = collection.find(dbObject);
        return Lists.newArrayList((Iterator<DBObject>) find);
    }

    public List<DBObject> findAll() {

        List<DBObject> allUsers = new LinkedList<>();

        DBCursor allDbObjects = collection.find();

        for (DBObject userDbObject : allDbObjects) {
            allUsers.add(userDbObject);
        }

        return allUsers;

    }

}
