package com.mercateo.db.mongo;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import com.mercateo.WicketConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class MongoDbAccess {

    private final MongoClient mongoClient;

    private final DBCollection collection;

    private final DB mongoDatabase;

    public MongoDbAccess() throws UnknownHostException {
        String mongoURIString = "mongodb://localhost";
        String dbName = "kitchen";
        String collectionName = "users";
        mongoClient = new MongoClient(new MongoClientURI(mongoURIString));
        mongoDatabase = mongoClient.getDB(dbName);
        collection = mongoDatabase.getCollection(collectionName);
    }

    public String sayHello() {
        insertOneDocument();
        List<DBObject> findAllDocument = findAllDocument();

        StringBuilder sb = new StringBuilder();
        sb.append("Hello Mongo: " + findAllDocument);
        return sb.toString();
    }

    private List<DBObject> findAllDocument() {

        BasicDBObject all = new BasicDBObject();
        DBCursor documents = collection.find(all);

        List<DBObject> allDocuments = new LinkedList<>();
        while (documents.hasNext()) {
            allDocuments.add(documents.next());
        }

        return allDocuments;
    }

    private void insertOneDocument() {

        String username = WicketConstants.USERNAME;
        String password = "passwordHash";

        BasicDBObject user = new BasicDBObject();

        user.append("_id", username).append(WicketConstants.PASSWORD, password);

        try {
            collection.remove(user);
            collection.insert(user);
        } catch (MongoException.DuplicateKey e) {
            System.out.println("Username already in use: " + username);
        }
    }

}
