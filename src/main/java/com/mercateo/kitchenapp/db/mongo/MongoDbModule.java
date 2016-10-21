package com.mercateo.kitchenapp.db.mongo;

import java.net.UnknownHostException;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.mercateo.kitchenapp.db.MealsDao;
import com.mercateo.kitchenapp.db.TimetableDao;
import com.mercateo.kitchenapp.db.SubscriptionsDao;
import com.mercateo.kitchenapp.db.UserDao;
import com.mercateo.kitchenapp.db.mongo.meals.MealsCollection;
import com.mercateo.kitchenapp.db.mongo.meals.MongoMealsDao;
import com.mercateo.kitchenapp.db.mongo.offer.MongoTimetableDao;
import com.mercateo.kitchenapp.db.mongo.offer.OfferCollection;
import com.mercateo.kitchenapp.db.mongo.subscriptions.MongoSubscriptionsDao;
import com.mercateo.kitchenapp.db.mongo.subscriptions.SubscriptionsCollection;
import com.mercateo.kitchenapp.db.mongo.users.MongoUserDao;
import com.mercateo.kitchenapp.db.mongo.users.UserCollection;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDao.class).to(MongoUserDao.class).in(Scopes.SINGLETON);
        bind(MealsDao.class).to(MongoMealsDao.class).in(Scopes.SINGLETON);
        bind(TimetableDao.class).to(MongoTimetableDao.class).in(Scopes.SINGLETON);
        bind(SubscriptionsDao.class).to(MongoSubscriptionsDao.class).in(Scopes.SINGLETON);
    }

    @Provides
    MongoClient provideMongoClient(MongoDbConfiguration config) throws UnknownHostException {
        return new MongoClient(new MongoClientURI(config.getMongoURIString()));
    }

    @Provides
    DB provideDb(MongoClient client, MongoDbConfiguration config) {
        return client.getDB(config.getDbName());
    }

    @Provides
    @Singleton
    public MongoDbConfiguration provideMongoDbConfig() {
        return new MongoDbConfiguration();
    }

    @Provides
    @Singleton
    public UserCollection provideUserCollection(DB db, MongoDbConfiguration config) {
        return new UserCollection(db.getCollection(config.getCollectionNameUsers()));
    }

    @Provides
    @Singleton
    public MealsCollection provideMealsCollection(DB db, MongoDbConfiguration config) {
        return new MealsCollection(db.getCollection(config.getCollectionNameMeals()));
    }

    @Provides
    @Singleton
    public OfferCollection provideOffersCollection(DB db, MongoDbConfiguration config) {
        return new OfferCollection(db.getCollection(config.getCollectionNameOffers()));
    }

    @Provides
    @Singleton
    public SubscriptionsCollection provideSubscriptionsCollection(DB db,
            MongoDbConfiguration config) {
        return new SubscriptionsCollection(db.getCollection(config
                .getCollectionNameSubscriptions()));
    }

}
