package com.mercateo.kitchenapp.db.mongo;

import java.net.UnknownHostException;

import javax.inject.Inject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbObjectCreator {

    private final MongoDbConfiguration mongoDbConfiguration;

    private final MongoClient mongoClient;

    private final DB mongoDatabase;

    @Inject
    MongoDbObjectCreator(MongoDbConfiguration mongoDbConfiguration) throws UnknownHostException {
        this.mongoDbConfiguration = mongoDbConfiguration;
        this.mongoClient = new MongoClient(new MongoClientURI(mongoDbConfiguration
                .getMongoURIString()));
        this.mongoDatabase = mongoClient.getDB(mongoDbConfiguration.getDbName());
    }

    MongoClient getClient() {
        return mongoClient;
    }

    public DBCollection getUserCollection() {
        return mongoDatabase.getCollection(mongoDbConfiguration.getCollectionNameUsers());
    }

    DB getMongoDatabase() {
        return mongoDatabase;
    }

}