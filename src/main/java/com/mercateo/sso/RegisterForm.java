package com.mercateo.sso;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.HomePage;
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

        this.usernameField = new TextField<>("username", Model.of(""));
        this.passwordField = new PasswordTextField("password", Model.of(""));
        this.emailField = new TextField<>("email", Model.of(""));

        add(usernameField);
        add(passwordField);
        add(emailField);
    }

    @Override
    public final void onSubmit() {

        Username username = Username.of(usernameField.getModelObject());
        Password password = Password.of(passwordField.getModelObject());
        Email email = Email.of(emailField.getModelObject());

        User user = new User(username, password, email);

        PageParameters pageParameters = new PageParameters();

        try {
            UserAccess userAccess = userAccessFactory.create();
            userAccess.addUser(user);
            pageParameters.add("msg", "created user");
        } catch (UserAlreadyExistsException e) {
            pageParameters.add("msg", "user already exists");
        } catch (UserAccessCreationException e) {
            pageParameters.add("msg", "internal error: " + e.getMessage());
        }

        setResponsePage(HomePage.class, pageParameters);

    }

}