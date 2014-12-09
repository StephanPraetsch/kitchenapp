package com.mercateo.sso;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
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
import com.mercateo.profile.User;

public class RegisterForm extends Form<Object> {

    private final PasswordTextField passwordField;

    private final TextField<String> emailField;

    private final UserAccessFactory userAccessFactory;

    public RegisterForm(String id, UserAccessFactory userAccessFactory) {
        super(id);
        this.userAccessFactory = userAccessFactory;

        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));

        add(passwordField);
        add(emailField);
    }

    @Override
    public final void onSubmit() {

        Email email = Email.of(emailField.getModelObject());
        Password password = Password.of(passwordField.getModelObject());

        User user = User.of(email, password);

        PageParameters pageParameters = new PageParameters();

        try {
            UserAccess userAccess = userAccessFactory.create();
            userAccess.addUser(user);
            pageParameters.add(WicketConstants.STATUS, "created user");
        } catch (UserAccessCreationException e) {
            pageParameters.add(WicketConstants.STATUS, "internal error: " + e.getMessage());
        } catch (EmailAlreadyExistsExcpetion e) {
            pageParameters.add(WicketConstants.STATUS, "email already exists");
        }

        setResponsePage(HomePage.class, pageParameters);

    }

}