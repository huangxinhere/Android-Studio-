package com.example.roomtext;

import android.app.DownloadManager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    //获取所有数据
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();
    //删除所有数据
    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
    + "last_name LIKE :last LIMIT 1")
    User findByName(String first,String last);

    @Insert
    void insertAll(User... users);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User... users);

    @Delete
    void delete(User user);

    @Update
    void updateUser(User... users);
}
