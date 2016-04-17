package com.mercateo.kitchenapp;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.mongo.MongoDbConfiguration;
import com.mercateo.kitchenapp.db.mongo.MongoDbObjectCreator;
import com.mercateo.kitchenapp.db.mongo.users.UserAccessMongoDb;
import com.mongodb.DBCollection;

public class MongoDbModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserAccess.class).to(UserAccessMongoDb.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public MongoDbConfiguration provideMongoDbConfig() {
        return new MongoDbConfiguration();
    }

    @Provides
    @Singleton
    public DBCollection provideDbCollection(MongoDbObjectCreator creator) {
        return creator.getUserCollection();
    }

}
