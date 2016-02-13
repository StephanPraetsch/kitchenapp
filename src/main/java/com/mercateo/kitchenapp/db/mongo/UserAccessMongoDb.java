package com.mercateo.kitchenapp.db.mongo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.EmailAlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.UserDoesNotExistException;
import com.mercateo.kitchenapp.sso.roles.UserRole;
import com.mongodb.DBObject;

public class UserAccessMongoDb implements UserAccess, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(UserAccessMongoDb.class);

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
    public void addUser(User user) throws EmailAlreadyExistsExcpetion {

        if (existsEmail(user)) {
            throw new EmailAlreadyExistsExcpetion(user);
        }

        userCollection.insert(transformerUserToDbObject.apply(user));

    }

    private boolean existsEmail(User user) {
        try {
            Optional<DBObject> userByEmail = userCollection.findOne(transformerUserToDbObject.apply(
                    User.of(user.getEmail(), null)));
            return userByEmail.isPresent();
        } catch (DuplicateFoundException e) {
            logger.error("error while finding email for " + user, e);
            return true;
        }
    }

    @Override
    public List<User> listAllUsers() {

        List<User> allUsers = new LinkedList<>();

        userCollection.findAllUsers().forEach((DBObject d) -> allUsers.add(transformerDbObjectToUser
                .apply(d)));

        return allUsers;

    }

    @Override
    public Set<UserRole> getUserRoles(User user) throws UserDoesNotExistException {

        try {
            Optional<DBObject> userDbObjectOptional = userCollection.findOne(
                    transformerUserToDbObject.apply(user));

            if (!userDbObjectOptional.isPresent()) {
                throw new UserDoesNotExistException(user);
            }

            return transformerDbObjectToUser.apply(userDbObjectOptional.get()).getUserRoles();
        } catch (DuplicateFoundException e) {
            logger.error("error while finding user " + user, e);
            throw new UserDoesNotExistException(user);
        }

    }

}
