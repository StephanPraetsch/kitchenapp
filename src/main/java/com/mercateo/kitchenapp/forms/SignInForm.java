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

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.pages.error.ErrorPage;
import com.mercateo.kitchenapp.pages.home.HomePage;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;
import com.mercateo.kitchenapp.util.WicketConstants;

public class SignInForm extends Form<Object> {

    private static final Logger logger = LoggerFactory.getLogger(SignInForm.class);

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    @Inject
    SignInForm() {
        super("signInForm");

        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));

        add(emailField);
        add(passwordField);

        setDefaultModel(new CompoundPropertyModel(this));

    }

    @Override
    public void onSubmit() {
        try {
            login();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void login() {

        Email email = Email.of(emailField.getModelObject());
        Password password = Password.of(passwordField.getModelObject());

        User user = User.of(email, password);

        boolean authResult = AuthenticatedWebSession.get().signIn(user);

        if (authResult) {
            continueToOriginalDestination();
        } else {
            wrongCredentials();
        }

    }

    private void wrongCredentials() {
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "wrong email or password");
        setResponsePage(HomePage.class, pageParameters);
    }

    private void handleException(Exception e) {
        logger.error("could not sign in", e);
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "error while sign in");
        setResponsePage(ErrorPage.class, pageParameters);
    }

}