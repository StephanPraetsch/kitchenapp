package com.mercateo.kitchenapp.pages;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Optional;

import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mercateo.kitchenapp.KitchenApp;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;
import com.mercateo.kitchenapp.sso.authorization.AuthorizationStrategyImpl;
import com.mercateo.kitchenapp.sso.authorization.UnauthorizedListenerImpl;

public class WicketTest {

    private final PagesRegistry pages = new PagesRegistry();

    @Mock
    protected UserAccess userAccess;

    protected Email email = Email.of("test-email");

    protected Password password = Password.of("test-password");

    protected WicketTester tester;

    @Before
    public void setUp() {
        initMocks(this);
        when(userAccess.get(any(), any())).thenReturn(Optional.empty());
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(UserAccess.class).toInstance(userAccess);
                bind(PagesRegistry.class).toInstance(pages);
                bind(IUnauthorizedComponentInstantiationListener.class).to(
                        UnauthorizedListenerImpl.class);
                bind(IAuthorizationStrategy.class).to(AuthorizationStrategyImpl.class);
            }
        });
        tester = new WicketTester(new KitchenApp(injector));
    }

    public void signIn(User user) {
        when(userAccess.get(user.getEmail(), user.getPassword())).thenReturn(Optional.of(user));
        AuthenticatedWebSession.get().signIn(user.getEmail(), user.getPassword());
    }

    @After
    public void tearDown() {
        tester.destroy();
    }

}
