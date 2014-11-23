package com.mercateo.sso;

public class User {

    private final Username username;

    private final Password password;

    private final Email email;

    public static User of(Username username, Password password) {
        return new User(username, password, null);
    }

    public static User of(Email email, Password password) {
        return new User(null, password, email);
    }

    public static User of(Username username, Password password, Email email) {
        return new User(username, password, email);
    }

    private User(Username username, Password password, Email email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [username=");
        builder.append(username);
        builder.append(", password=");
        builder.append(password);
        builder.append(", email=");
        builder.append(email);
        builder.append("]");
        return builder.toString();
    }

}
