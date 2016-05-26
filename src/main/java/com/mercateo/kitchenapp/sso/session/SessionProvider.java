package com.mercateo.kitchenapp.sso.session;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.mercateo.kitchenapp.db.UserDao;
import com.mercateo.kitchenapp.sso.authorization.UserWebSession;

public class SessionProvider {

    private final UserDao userAccess;

    @Inject
    SessionProvider(UserDao userAccess) {
        this.userAccess = checkNotNull(userAccess);
    }

    public Session newSession(Request request, Response response) {
        checkNotNull(request);
        checkNotNull(response);
        return new UserWebSession(request, userAccess);
    }

}
