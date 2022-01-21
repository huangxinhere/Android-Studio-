package com.example.myapplication;

import android.arch.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWords(Word... words);

    @Delete
    void deleteWords(Word... words);

    @Update//修改
    void updateWords(Word... words);

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    LiveData<List<Word>> getAllWords();

    @Query("DELETE FROM WORD")
    void deleteAllWords();


}
