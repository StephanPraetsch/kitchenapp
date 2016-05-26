package com.mercateo.kitchenapp.forms;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.AlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.UserDao;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;
import com.mercateo.kitchenapp.util.WicketConstants;

public class SignUpForm extends Form<User> {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(SignUpForm.class);

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    @Inject
    private Provider<Md5Hasher> md5Hasher;

    @Inject
    private UserDao userAccess;

    @Inject
    private PagesRegistry pagesRegistry;

    public SignUpForm() {
        super("signUpForm");

        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));

        add(emailField);
        add(passwordField);

        setDefaultModel(new CompoundPropertyModel<>(this));

    }

    @Override
    public final void onSubmit() {

        try {
            signUp();
        } catch (AlreadyExistsExcpetion e) {
            emailAlreadyExists();
        } catch (Exception e) {
            handleException(e);
        }

    }

    private void signUp() throws AlreadyExistsExcpetion {

        Email email = Email.of(emailField.getModelObject());
        Password password = Password.of(md5Hasher.get().hash(passwordField.getModelObject()));

        User user = User.builder().email(email).password(password).build();

        userAccess.addUser(user);

        AuthenticatedWebSession.get().signIn(email, password);

        setResponsePage(pagesRegistry.getProfilePage());

    }

    private void emailAlreadyExists() {
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "email already exists, try another one");
        setResponsePage(pagesRegistry.getSignInPage(), pageParameters);
    }

    private void handleException(Exception e) {
        logger.error("could not sign up", e);
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "error while sign up");
        setResponsePage(pagesRegistry.getErrorPage(), pageParameters);
    }

}
