package com.example.shadiassignment;

import com.example.shadiassignment.Model.User;
import com.example.shadiassignment.Roomdb.UserEntity;

import java.util.ArrayList;

interface OnResponseReceived {
    void responseSuccess(UserEntity user);
    void responseSuccessComplete(ArrayList<UserEntity> users);
}