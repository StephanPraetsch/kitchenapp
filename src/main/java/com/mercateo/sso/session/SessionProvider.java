package com.mercateo.sso.session;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.mercateo.sso.authorization.BasicAuthenticationSession;

public class SessionProvider {

    public Session newSession(Request request, Response response) {
        return new BasicAuthenticationSession(request);
    }

}
