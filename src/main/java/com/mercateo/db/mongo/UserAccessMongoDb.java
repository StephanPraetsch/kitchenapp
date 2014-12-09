package com.mercateo.db.mongo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mercateo.db.EmailAlreadyExistsExcpetion;
import com.mercateo.db.EmailDoesNotExistException;
import com.mercateo.db.UserAccess;
import com.mercateo.profile.Email;
import com.mercateo.profile.User;
import com.mercateo.sso.UserRole;
import com.mongodb.DBObject;

class UserAccessMongoDb implements UserAccess, Serializable {

    private static final long serialVersionUID = 1L;

    private final UserCollection userCollection;

    private final TransformerDbObjectToUser transformerDbObjectToUser;

    private final TransformerUserToDbObject transformerUserToDbObject;

    UserAccessMongoDb(UserCollection userCollection,
            TransformerDbObjectToUser transformerDbObjectToUser,
            TransformerUserToDbObject transformerUserToDbObject) {
        this.userCollection = userCollection;
        this.transformerDbObjectToUser = transformerDbObjectToUser;
        this.transformerUserToDbObject = transformerUserToDbObject;
    }

    @Override
    public boolean existsUser(User user) {
        return existsEmail(user);
    }

    private boolean existsEmail(User user) {

        Email email = user.getEmail();
        if (email == null) {
            return false;
        }

        Optional<DBObject> userByEmail = userCollection.getUser(email);

        return userByEmail.isPresent();

    }

    @Override
    public void addUser(User user) throws EmailAlreadyExistsExcpetion {

        if (existsEmail(user)) {
            throw new EmailAlreadyExistsExcpetion(user);
        }

        userCollection.insert(transformerUserToDbObject.apply(user));

    }

    @Override
    public List<User> listAllUsers() {

        List<User> allUsers = new LinkedList<>();

        userCollection.findAllUsers().forEach(
                (DBObject d) -> allUsers.add(transformerDbObjectToUser.apply(d)));

        return allUsers;

    }

    @Override
    public Set<UserRole> getUserRoles(Email email) throws EmailDoesNotExistException {

        Optional<DBObject> userByEmail = userCollection.getUser(email);

        if (!userByEmail.isPresent()) {
            throw new EmailDoesNotExistException(email);
        }

        User user = transformerDbObjectToUser.apply(userByEmail.get());

        return user.getUserRoles();

    }

}
