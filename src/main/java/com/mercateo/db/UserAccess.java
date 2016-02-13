package com.mercateo.db;

import java.util.List;
import java.util.Set;

import com.mercateo.data.User;
import com.mercateo.sso.roles.UserRole;

public interface UserAccess {

    public boolean existsUser(User user);

    public void addUser(User user) throws EmailAlreadyExistsExcpetion;

    public List<User> listAllUsers();

    public Set<UserRole> getUserRoles(User user) throws UserDoesNotExistException;

}
