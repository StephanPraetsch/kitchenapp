package com.mercateo.kitchenapp.forms;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.EmailAlreadyExistsExcpetion;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.pages.profile.ProfilePage;
import com.mercateo.kitchenapp.pages.signin.SignInPage;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;
import com.mercateo.kitchenapp.util.WicketConstants;

public class SignUpForm extends Form<Object> {

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    private final UserAccess userAccess;

    @Inject
    SignUpForm(UserAccess userAccess) {
        super("signUpForm");
        this.userAccess = checkNotNull(userAccess);

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

            Email email = Email.of(emailField.getModelObject());
            Password password = Password.of(passwordField.getModelObject());

            User user = User.of(email, password);

            userAccess.addUser(user);

            AuthenticatedWebSession.get().signIn(email.asString(), password.asString());

            setResponsePage(ProfilePage.class, pageParameters);

        } catch (EmailAlreadyExistsExcpetion e) {
            pageParameters.add(WicketConstants.STATUS, "email already exists, try another one");
            setResponsePage(SignInPage.class, pageParameters);
        }

    }

}
