package com.mercateo.kitchenapp.db.mongo;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DuplicateFoundException extends Exception {

    public DuplicateFoundException(DBObject dbObject, DBCursor cursor) {
        super("duplicate found for '" + dbObject + "' : " + cursor);
    }

}
