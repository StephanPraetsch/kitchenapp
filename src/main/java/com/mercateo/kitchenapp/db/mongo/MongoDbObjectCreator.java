package com.mercateo.kitchenapp.db.mongo;

import java.net.UnknownHostException;

import javax.inject.Inject;

import com.mercateo.kitchenapp.db.mongo.chips.ChipsCollection;
import com.mercateo.kitchenapp.db.mongo.meals.MealsCollection;
import com.mercateo.kitchenapp.db.mongo.users.UserCollection;
import com.mongodb.DB;
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

    public UserCollection getUserCollection() {
        return new UserCollection(mongoDatabase.getCollection(mongoDbConfiguration
                .getCollectionNameUsers()));
    }

    public MealsCollection getMealsCollection() {
        return new MealsCollection(mongoDatabase.getCollection(mongoDbConfiguration
                .getCollectionNameUsers()));
    }

    public ChipsCollection getChipsCollection() {
        return new ChipsCollection(mongoDatabase.getCollection(mongoDbConfiguration
                .getCollectionNameChips()));
    }

}
