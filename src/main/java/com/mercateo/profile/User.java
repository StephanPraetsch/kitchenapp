package com.mercateo.profile;

import java.util.HashSet;
import java.util.Set;

import com.mercateo.sso.authorization.UserRole;

public class User {

    private Email email;

    private Password password;

    private Set<UserRole> userRoles = new HashSet<>();

    public static User of(Email email, Password password) {
        return new User(email, password);
    }

    private User(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public void addUserRole(UserRole userRole) {
        userRoles.add(userRole);
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

}
