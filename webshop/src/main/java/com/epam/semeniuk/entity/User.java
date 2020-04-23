package com.epam.semeniuk.entity;

import com.epam.semeniuk.enums.Role;

public class User {

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean spam;
    private String avatarExtension;
    private Role role;

    public User() {
    }

    public User(String login, String firstName, String lastName, String email, String password, boolean spam, String avatarName, Role role) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.spam = spam;
        this.avatarExtension = avatarName;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public boolean isSpam() {
        return spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }

    public String getAvatarExtension() {
        return avatarExtension;
    }

    public void setAvatarExtension(String avatarExtension) {
        this.avatarExtension = avatarExtension;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", spam=" + spam +
                '}';
    }
}
