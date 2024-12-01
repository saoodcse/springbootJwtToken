package com.saood.modules.registration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class RegistrationRequest {
    @JsonProperty(value = "userName")
    private String username;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "dob")
    private String dob;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "middleName")
    private String middleName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "mobileNumber")
    private String mobileNumber;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "country_name")
    private String countryName;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public RegistrationRequest(String username, String password, String dob, String firstName, String middleName, String lastName, String mobileNumber, String email, String countryName) {
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.countryName = countryName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public void validate() {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (dob == null || dob.isEmpty()) {
            throw new IllegalArgumentException("Date of Birth (DOB) cannot be null or empty");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be null or empty");
        }
        if (countryName == null || countryName.isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be null or empty");
        }
    }
}
