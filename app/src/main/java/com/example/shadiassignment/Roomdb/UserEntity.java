package com.example.shadiassignment.Roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "dob")
    String dob;

    @ColumnInfo(name = "email")
    String email;

    @ColumnInfo(name = "picture")
    String picture;

    @ColumnInfo(name = "phone_number")
    String phone_number;

    @ColumnInfo(name = "is_accepted_or_declined")
    String isAcceptedOrDeclined="---";

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}