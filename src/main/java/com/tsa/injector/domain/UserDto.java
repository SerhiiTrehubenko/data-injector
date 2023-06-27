package com.tsa.injector.domain;

import java.util.Objects;

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String nickName;

    public boolean isFull() {
        return Objects.nonNull(firstName) &&
                Objects.nonNull(lastName) &&
                Objects.nonNull(email) &&
                Objects.nonNull(nickName);
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        String asString =  "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
        return asString.replaceAll("null", "[WRONG FORMAT]");
    }
}
