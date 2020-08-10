package com.example.shadiassignment.Model;

public class User{
    String gender;
    String first_name;
    String last_name;
    String name;
    String dob;
    String email;
    String picture;
    String phone_number;
    String isAcceptedOrDeclined="---";

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIsAcceptedOrDeclined() {
        return isAcceptedOrDeclined;
    }

    public void setIsAcceptedOrDeclined(String isAcceptedOrDeclined) {
        this.isAcceptedOrDeclined = isAcceptedOrDeclined;
    }
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}