package com.mercateo.db;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mercateo.db.dummy.DummyUserAccessFactory;
import com.mercateo.db.mongo.MongoDbConfiguration;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.sso.Authenticator;

public class UserAccessFactoryCache {

    private static final Logger logger = Logger.getLogger(Authenticator.class);

    private static final Map<Class<? extends UserAccessFactory>, UserAccessFactory> CACHE = new HashMap<>();

    public static UserAccessFactory get() {
        try {
            return userAccessFactoryBuiltByRegisteredClass();
        } catch (ClassNotFoundException e) {
            return handleClassNotFoundException(e);
        }
    }

    private static UserAccessFactory userAccessFactoryBuiltByRegisteredClass()
            throws ClassNotFoundException {

        Class<? extends UserAccessFactory> userAccessFactoryClass = determineUserAccessFactoryClass();

        UserAccessFactory userAccessFactory = getUserAccessFactoryFromCache(userAccessFactoryClass);

        if (userAccessFactory != null) {
            return userAccessFactory;
        }

        userAccessFactory = createUserAccessFactory(userAccessFactoryClass);

        if (userAccessFactory != null) {
            putIntoCache(userAccessFactoryClass, userAccessFactory);
            return userAccessFactory;
        }

        throw newClassNotFoundException(userAccessFactoryClass);

    }

    private static Class<? extends UserAccessFactory> determineUserAccessFactoryClass()
            throws ClassNotFoundException {

        @SuppressWarnings("unchecked")
        Class<? extends UserAccessFactory> userAccessFactoryClass = (Class<? extends UserAccessFactory>) Class
                .forName(System.getProperty(UserAccessFactory.USER_ACCESS_FACTORY));

        return userAccessFactoryClass;

    }

    private static UserAccessFactory getUserAccessFactoryFromCache(
            Class<? extends UserAccessFactory> userAccessFactoryClass) {

        UserAccessFactory userAccessFactory = null;
        synchronized (CACHE) {
            userAccessFactory = CACHE.get(userAccessFactoryClass);
        }
        return userAccessFactory;

    }

    private static UserAccessFactory createUserAccessFactory(
            Class<? extends UserAccessFactory> userAccessFactoryClass) {

        if (userAccessFactoryClass == UserAccessFactoryForMongoDb.class) {
            return new UserAccessFactoryForMongoDb(new MongoDbConfiguration());
        }

        return null;

    }

    private static void putIntoCache(Class<? extends UserAccessFactory> userAccessFactoryClass,
            UserAccessFactory userAccessFactory) {

        synchronized (CACHE) {
            CACHE.put(userAccessFactoryClass, userAccessFactory);
        }

    }

    private static ClassNotFoundException newClassNotFoundException(
            Class<? extends UserAccessFactory> userAccessFactoryClass) {

        return new ClassNotFoundException("could not create UserAccessFactory with '"
                + userAccessFactoryClass + "'");

    }

    private static UserAccessFactory handleClassNotFoundException(ClassNotFoundException e) {
        logger.fatal("could not create UserAccessFactory, using dummy instead", e);
        return DummyUserAccessFactory.INSTANCE;
    }

}
