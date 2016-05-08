package com.mercateo.kitchenapp.forms;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;
import com.mercateo.kitchenapp.util.WicketConstants;

public class SignInForm extends Form<Object> {

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    @Inject
    private Md5Hasher md5Hasher;

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
        login();
    }

    private void login() {

        Email email = Email.of(emailField.getModelObject());
        Password password = Password.of(md5Hasher.hash(passwordField.getModelObject()));

        boolean authResult = AuthenticatedWebSession.get().signIn(email, password);

        if (authResult) {
            continueToOriginalDestination();
        } else {
            wrongCredentials();
        }

    }

    private void wrongCredentials() {
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(WicketConstants.STATUS, "wrong email or password");
        setResponsePage(WicketGuiceHelper.get().getInstance(PagesRegistry.class).getSignInPage(),
                pageParameters);
    }

}
