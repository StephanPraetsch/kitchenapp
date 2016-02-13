package com.mercateo.sso.session;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.mercateo.pages.PagesRegistry;
import com.mercateo.sso.authorization.AbstractAuthenticatedWebSession;

public class SessionProvider {

    private final PagesRegistry pages;

    @Inject
    SessionProvider(PagesRegistry pages) {
        this.pages = pages;
    }

    public Session newSession(Request request, Response response) {
        Class<? extends AbstractAuthenticatedWebSession> webSessionClass = pages
                .getWebSessionClass();
        try {
            return webSessionClass.getDeclaredConstructor(Request.class).newInstance(request);
        } catch (Exception e) {
            throw new WicketRuntimeException("Unable to instantiate web session " + webSessionClass,
                    e);
        }
    }

}
