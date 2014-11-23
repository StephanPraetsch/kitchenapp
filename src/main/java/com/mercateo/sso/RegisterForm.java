package com.mercateo.sso;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.HomePage;
import com.mercateo.WicketConstants;
import com.mercateo.db.mongo.EmailAlreadyExistsExcpetion;
import com.mercateo.db.mongo.UserAccess;
import com.mercateo.db.mongo.UserAccessCreationException;
import com.mercateo.db.mongo.UserAccessFactory;
import com.mercateo.db.mongo.UserAlreadyExistsException;

public class RegisterForm extends Form<Object> {

    private final TextField<String> usernameField;

    private final PasswordTextField passwordField;

    private final TextField<String> emailField;

    private final UserAccessFactory userAccessFactory;

    public RegisterForm(String id, UserAccessFactory userAccessFactory) {
        super(id);
        this.userAccessFactory = userAccessFactory;

        this.usernameField = new TextField<>(WicketConstants.USERNAME, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));
        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));

        add(usernameField);
        add(passwordField);
        add(emailField);
    }

    @Override
    public final void onSubmit() {

        Username username = Username.of(usernameField.getModelObject());
        Password password = Password.of(passwordField.getModelObject());
        Email email = Email.of(emailField.getModelObject());

        User user = User.of(username, password, email);

        PageParameters pageParameters = new PageParameters();

        try {
            UserAccess userAccess = userAccessFactory.create();
            userAccess.addUser(user);
            pageParameters.add(WicketConstants.STATUS, "created user");
        } catch (UserAccessCreationException e) {
            pageParameters.add(WicketConstants.STATUS, "internal error: " + e.getMessage());
        } catch (UserAlreadyExistsException e) {
            pageParameters.add(WicketConstants.STATUS, "user already exists");
        } catch (EmailAlreadyExistsExcpetion e) {
            pageParameters.add(WicketConstants.STATUS, "email already exists");
        }

        setResponsePage(HomePage.class, pageParameters);

    }

}