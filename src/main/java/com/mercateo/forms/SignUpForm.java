package com.mercateo.forms;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.HomePage;
import com.mercateo.WicketConstants;
import com.mercateo.db.EmailAlreadyExistsExcpetion;
import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.profile.Email;
import com.mercateo.profile.Password;
import com.mercateo.profile.ProfilePage;
import com.mercateo.profile.User;
import com.mercateo.sso.SignInPage;
import com.mercateo.sso.authorization.AuthenticatedWebSession;

public class SignUpForm extends Form<Object> {

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    private final UserAccessFactory userAccessFactory;

    @Inject
    SignUpForm(UserAccessFactory userAccessFactory) {
        super("signUpForm");
        this.userAccessFactory = userAccessFactory;

        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));

        add(emailField);
        add(passwordField);

        setDefaultModel(new CompoundPropertyModel(this));

    }

    @Override
    public final void onSubmit() {

        PageParameters pageParameters = new PageParameters();

        try {
            UserAccess userAccess = userAccessFactory.create();

            Email email = Email.of(emailField.getModelObject());
            Password password = Password.of(passwordField.getModelObject());

            User user = User.of(email, password);

            userAccess.addUser(user);

            AuthenticatedWebSession.get().signIn(email.asString(), password.asString());

            setResponsePage(ProfilePage.class, pageParameters);

        } catch (UserAccessCreationException e) {
            pageParameters.add(WicketConstants.STATUS, "internal error: '" + e.getMessage() + "'");
            setResponsePage(HomePage.class, pageParameters);
        } catch (EmailAlreadyExistsExcpetion e) {
            pageParameters.add(WicketConstants.STATUS, "email already exists, try another one");
            setResponsePage(SignInPage.class, pageParameters);
        }

    }

}
