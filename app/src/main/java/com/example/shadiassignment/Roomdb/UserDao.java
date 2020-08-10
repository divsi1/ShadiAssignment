package com.example.shadiassignment.Roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAll();

    @Insert
    void insert(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

    @Update
    void update(UserEntity userEntity);

    @Query("UPDATE UserEntity SET is_accepted_or_declined=:isAcceptedOrDeclined WHERE email = :email")
    void update(String isAcceptedOrDeclined, String email);
}