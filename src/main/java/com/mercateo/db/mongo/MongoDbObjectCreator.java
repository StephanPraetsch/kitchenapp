package com.mercateo.db.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbObjectCreator {

    private final MongoDbConfiguration mongoDbConfiguration;

    private final MongoClient mongoClient;

    private final DB mongoDatabase;

    public MongoDbObjectCreator(MongoDbConfiguration mongoDbConfiguration)
            throws UnknownHostException {
        this.mongoDbConfiguration = mongoDbConfiguration;
        this.mongoClient = new MongoClient(new MongoClientURI(mongoDbConfiguration
                .getMongoURIString()));
        this.mongoDatabase = mongoClient.getDB(mongoDbConfiguration.getDbName());
    }

    public MongoClient getClient() {
        return mongoClient;
    }

    public DBCollection getUserCollection() {
        return mongoDatabase.getCollection(mongoDbConfiguration.getCollectionNameUsers());
    }

    public DB getMongoDatabase() {
        return mongoDatabase;
    }

}
