package com.saood.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "registration")
public class RegistrationEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "registration_id", nullable = false, unique = true, length = 50)
    private String registrationId;

    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "middle_name", nullable = true, length = 100)
    private String middleName;

    @Column(name = "last_name", nullable = true, length = 100)
    private String lastName;

    @Column(name = "dob", nullable = true, length = 100)
    private String dob;

    @Column(name = "mobile_number", nullable = true, length = 100)
    private String mobileNumber;

    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Column(name = "country_name", nullable = true, length = 100)
    private String countryName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegistrationEntity() {
    }

    public RegistrationEntity(String registrationId, String username, String firstName, String middleName, String lastName, String mobileNumber, String dob, String email, String countryName, UserEntity userEntity) {
        this.registrationId = registrationId;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.email = email;
        this.countryName = countryName;
        this.userEntity = userEntity;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
