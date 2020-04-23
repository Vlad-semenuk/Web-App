package com.epam.semeniuk.dto;

import com.epam.semeniuk.entity.User;
import com.epam.semeniuk.enums.Role;

import java.util.Objects;

public class UserDTO {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean spam;
    private String avatarExtension;

    public User toUser() {
        return new User(login, firstName, lastName, email, password, spam, avatarExtension, Role.USER);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isSpam() {
        return spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarExtension() {
        return avatarExtension;
    }

    public void setAvatarExtension(String avatarExtension) {
        this.avatarExtension = avatarExtension;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "login = " + lastName + '\'' +
                ",firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", email = " + email + '\'' +
                ", checkbox = '" + spam + '\'' +
                '}';
    }
}
