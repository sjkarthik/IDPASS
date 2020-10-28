package org.idpass.lite;

public class IdentFields {
    private String UIN;
    private String gender;
    private String givenName;
    private String surName;
    private String placeOfBirth;
    private String dateOfBirth;
    private String pin;
    private String address;

    public IdentFields() {
    }

    public IdentFields(String UIN, String gender, String givenName, String surName, String placeOfBirth, String dateOfBirth, String pin, String address) {
        this.UIN = UIN;
        this.gender = gender;
        this.givenName = givenName;
        this.surName = surName;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.pin = pin;
        this.address = address;
    }

    public String getUIN() {
        return UIN;
    }

    public void setUIN(String UIN) {
        this.UIN = UIN;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
