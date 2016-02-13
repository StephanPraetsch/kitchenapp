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

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.EmailAlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;
import com.mercateo.kitchenapp.util.WicketConstants;

public class SignUpForm extends Form<Object> {

    private static final Logger logger = LoggerFactory.getLogger(SignUpForm.class);

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    @Inject
    SignUpForm() {
        super("signUpForm");

        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));

        add(emailField);
        add(passwordField);

        setDefaultModel(new CompoundPropertyModel(this));

    }

    @Override
    public final void onSubmit() {

        try {
            signUp();
        } catch (EmailAlreadyExistsExcpetion e) {
            emailAlreadyExists();
        } catch (Exception e) {
            handleException(e);
        }

    }

    private void signUp() throws EmailAlreadyExistsExcpetion {

        Email email = Email.of(emailField.getModelObject());
        Password password = Password.of(passwordField.getModelObject());

        User user = User.of(email, password);

        WicketGuiceHelper.get().getInstance(UserAccess.class).addUser(user);

        AuthenticatedWebSession.get().signIn(user);

        setResponsePage(WicketGuiceHelper.get().getInstance(PagesRegistry.class).getProfilePage());

    }

    private void emailAlreadyExists() {
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "email already exists, try another one");
        setResponsePage(WicketGuiceHelper.get().getInstance(PagesRegistry.class).getSignInPage(),
                pageParameters);
    }

    private void handleException(Exception e) {
        logger.error("could not sign up", e);
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "error while sign up");
        setResponsePage(WicketGuiceHelper.get().getInstance(PagesRegistry.class).getErrorPage(),
                pageParameters);
    }

}
