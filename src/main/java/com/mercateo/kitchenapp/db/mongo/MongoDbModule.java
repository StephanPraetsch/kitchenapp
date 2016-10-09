package com.mercateo.kitchenapp.db.mongo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.mercateo.kitchenapp.db.MealsDao;
import com.mercateo.kitchenapp.db.OffersDao;
import com.mercateo.kitchenapp.db.SubscriptionsDao;
import com.mercateo.kitchenapp.db.UserDao;
import com.mercateo.kitchenapp.db.mongo.meals.MealsCollection;
import com.mercateo.kitchenapp.db.mongo.meals.MongoMealsDao;
import com.mercateo.kitchenapp.db.mongo.offers.MongoOffersDao;
import com.mercateo.kitchenapp.db.mongo.offers.OffersCollection;
import com.mercateo.kitchenapp.db.mongo.subscriptions.MongoSubscriptionsDao;
import com.mercateo.kitchenapp.db.mongo.subscriptions.SubscriptionsCollection;
import com.mercateo.kitchenapp.db.mongo.users.MongoUserDao;
import com.mercateo.kitchenapp.db.mongo.users.UserCollection;

public class MongoDbModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDao.class).to(MongoUserDao.class).in(Scopes.SINGLETON);
        bind(MealsDao.class).to(MongoMealsDao.class).in(Scopes.SINGLETON);
        bind(OffersDao.class).to(MongoOffersDao.class).in(Scopes.SINGLETON);
        bind(SubscriptionsDao.class).to(MongoSubscriptionsDao.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public MongoDbConfiguration provideMongoDbConfig() {
        return new MongoDbConfiguration();
    }

    @Provides
    @Singleton
    public UserCollection provideUserCollection(MongoDbObjectCreator creator) {
        return creator.getUserCollection();
    }

    @Provides
    @Singleton
    public MealsCollection provideMealsCollection(MongoDbObjectCreator creator) {
        return creator.getMealsCollection();
    }

    @Provides
    @Singleton
    public OffersCollection provideOffersCollection(MongoDbObjectCreator creator) {
        return creator.getOffersCollection();
    }

    @Provides
    @Singleton
    public SubscriptionsCollection provideSubscriptionsCollection(MongoDbObjectCreator creator) {
        return creator.getSubscriptionsCollection();
    }

}
