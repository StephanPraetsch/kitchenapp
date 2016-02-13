package com.mercateo.sso;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

import com.mercateo.HomePage;
import com.mercateo.WicketConstants;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.sso.authorization.AuthenticatedWebSession;

public class SignInForm extends Form<Object> {

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    public SignInForm(String id, UserAccessFactory userAccessFactory) {
        super(id);

        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));

        add(emailField);
        add(passwordField);

        setDefaultModel(new CompoundPropertyModel(this));

    }

    @Override
    public final void onSubmit() {
        login();
    }

    private void login() {

        String email = emailField.getModelObject();
        String password = passwordField.getModelObject();

        if (Strings.isEmpty(email) || Strings.isEmpty(password)) {
            return;
        }

        boolean authResult = AuthenticatedWebSession.get().signIn(email, password);

        if (authResult) {
            continueToOriginalDestination();
        } else {
            couldNotLogIn();
        }

    }

    private void couldNotLogIn() {
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "could not log in");
        setResponsePage(HomePage.class, pageParameters);
    }

}
