package com.mercateo.sso;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

import com.mercateo.layout.HeaderMiddleFooterTemplate;

public class SignInPage extends HeaderMiddleFooterTemplate {

    private String username;

    private String password;

    public SignInPage() {

        StatelessForm form = new StatelessForm("form") {
            @Override
            protected void onSubmit() {
                if (Strings.isEmpty(username) || Strings.isEmpty(password))
                    return;

                boolean authResult = AuthenticatedWebSession.get().signIn(username, password);

                if (authResult) {
                    continueToOriginalDestination();
                } else {
                    SignInPage.this.replace(new Label(MESSAGE_ID, "wrong username or password"));
                }
            }
        };

        // TODO dadurch wird set+getUsername und set+getPassword gerufen, auch
        // wenn sie nicht definiert sind
        form.setDefaultModel(new CompoundPropertyModel(this));

        form.add(new TextField("username"));
        form.add(new PasswordTextField("password"));

        add(form);

    }

}
