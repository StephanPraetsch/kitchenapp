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

public class LoginForm extends Form<Object> {

    private final TextField<String> usernameOrEmailField;

    private final PasswordTextField passwordField;

    private final UserAccessFactory userAccessFactory;

    public LoginForm(String id, UserAccessFactory userAccessFactory) {
        super(id);
        this.userAccessFactory = userAccessFactory;

        this.usernameOrEmailField = new TextField<>("usernameOrEmail", Model.of(""));
        this.passwordField = new PasswordTextField("password", Model.of(""));

        add(usernameOrEmailField);
        add(passwordField);
    }

    @Override
    public final void onSubmit() {

        PageParameters pageParameters = new PageParameters();

        try {

            UserAccess userAccess = userAccessFactory.create();

            // TODO awful wording / flow
            if (!logInByUsername(pageParameters, userAccess)) {
                if (!logInByEmail(pageParameters, userAccess)) {
                    couldNotLogIn(pageParameters);
                }
            }

        } catch (UserAccessCreationException e) {
            pageParameters.add("msg", "internal error: '" + e.getMessage() + "'");
        }

        setResponsePage(HomePage.class, pageParameters);

    }

    private boolean logInByUsername(PageParameters pageParameters, UserAccess userAccess) {
        String username = usernameOrEmailField.getModelObject();
        String password = passwordField.getModelObject();
        User userOfUsername = User.of(Username.of(username), Password.of(password));
        if (userAccess.userExists(userOfUsername)) {
            pageParameters.add("msg", "logged in by username '" + username + "'");
            return true;
        }
        return false;
    }

    private boolean logInByEmail(PageParameters pageParameters, UserAccess userAccess) {
        String email = usernameOrEmailField.getModelObject();
        String password = passwordField.getModelObject();
        User userOfEmail = User.of(Email.of(email), Password.of(password));
        if (userAccess.userExists(userOfEmail)) {
            pageParameters.add("msg", "logged in by email '" + email + "'");
            return true;
        }
        return false;
    }

    private void couldNotLogIn(PageParameters pageParameters) {
        pageParameters.add("msg", "could not log in");
    }

}
