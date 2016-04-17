package com.mercateo.kitchenapp.db.mongo.users;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.AlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.mongo.DuplicateFoundException;
import com.mongodb.DBObject;

public class UserAccessMongoDb implements UserAccess, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(UserAccessMongoDb.class);

    private final UserCollection userCollection;

    private final TransformerDbObjectToUser transformerDbObjectToUser;

    private final TransformerUserToDbObject transformerUserToDbObject;

    @Inject
    UserAccessMongoDb(UserCollection userCollection,
            TransformerDbObjectToUser transformerDbObjectToUser,
            TransformerUserToDbObject transformerUserToDbObject) {
        this.userCollection = userCollection;
        this.transformerDbObjectToUser = transformerDbObjectToUser;
        this.transformerUserToDbObject = transformerUserToDbObject;
    }

    @Override
    public boolean existsUser(User user) {
        try {
            Optional<DBObject> userByEmail = userCollection.findOne(transformerUserToDbObject.apply(
                    user));
            return userByEmail.isPresent();
        } catch (DuplicateFoundException e) {
            logger.error("error while finding user " + user, e);
            return true;
        }
    }

    @Override
    public void addUser(User user) throws AlreadyExistsExcpetion {

        if (existsEmail(user)) {
            throw new AlreadyExistsExcpetion(user);
        }

        userCollection.insert(transformerUserToDbObject.apply(user));

    }

    private boolean existsEmail(User user) {
        try {
            Optional<DBObject> userByEmail = userCollection.findOne(transformerUserToDbObject.apply(
                    User.builder().email(user.getEmail()).build()));
            return userByEmail.isPresent();
        } catch (DuplicateFoundException e) {
            logger.error("error while finding email for " + user, e);
            return true;
        }
    }

    @Override
    public List<User> listAllUsers() {

        List<User> allUsers = new LinkedList<>();

        userCollection.findAll().forEach((DBObject d) -> allUsers.add(transformerDbObjectToUser
                .apply(d)));

        return allUsers;

    }

    @Override
    public Optional<User> get(Email email, Password password) {

        checkNotNull(email);
        checkNotNull(password);

        try {

            User user = User.builder().email(email).password(password).build();

            return userCollection.findOne( //
                    transformerUserToDbObject.apply(user)) //
                    .map(transformerDbObjectToUser::apply);

        } catch (DuplicateFoundException e) {

            logger.error("error while finding use by email and password: " + email, e);
            return Optional.empty();

        }

    }

    @Override
    public Optional<User> get(Email email) {

        checkNotNull(email);

        try {

            User user = User.builder().email(email).build();

            return userCollection.findOne( //
                    transformerUserToDbObject.apply(user)) //
                    .map(transformerDbObjectToUser::apply);

        } catch (DuplicateFoundException e) {

            logger.error("error while finding use by email: " + email, e);
            return Optional.empty();

        }

    }

}
