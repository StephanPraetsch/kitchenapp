package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserDao;
import com.mercateo.kitchenapp.sso.roles.UserRole;

public class UserWebSession extends AuthenticatedWebSession {

    public static UserWebSession get() {
        return (UserWebSession) Session.get();
    }

    private final UserDao userAccess;

    private Optional<User> user = Optional.empty();

    public UserWebSession(Request request, UserDao userAccess) {
        super(request);
        this.userAccess = checkNotNull(userAccess);
    }

    @SuppressWarnings("boxing")
    @Override
    public boolean authenticate(Email email, Password password) {
        this.user = userAccess.get(email, password);
        return user.isPresent();
    }

    public Set<UserRole> getRoles() {
        return user.map(User::getUserRoles).orElse(Collections.emptySet());
    }

    public User getUser() {
        return user.orElseThrow(() -> new IllegalStateException("session has no user"));
    }

}
