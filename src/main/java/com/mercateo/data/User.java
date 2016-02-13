package com.mercateo.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.mercateo.sso.roles.UserRole;

public class User {

    private Email email;

    private Password password;

    private Set<UserRole> userRoles = new HashSet<>();

    public static User of(Email email, Password password) {
        return builder().email(email).password(password).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final User user = new User();

        public Builder email(Email email) {
            user.email = email;
            return this;
        }

        public Builder password(Password password) {
            user.password = password;
            return this;
        }

        public Builder userRoles(Set<UserRole> userRoles) {
            user.userRoles = userRoles;
            return this;
        }

        public User build() {
            return user;
        }

    }

    private User() {
        // for builder
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Set<UserRole> getUserRoles() {
        return Collections.unmodifiableSet(userRoles);
    }

}
