package com.ramireddy.ramwikitask.roomdata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ramireddy.ramwikitask.model.Page;

import java.util.List;
@Dao
public interface WikiDAO
{
    @Insert
    void insert(Page word);

    @Query("SELECT * from Page ORDER BY pageid ASC")
    LiveData<List<Page>> getAllWords();

    @Query("SELECT COUNT(pageid) FROM Page")
    LiveData<Integer> getRowCount();
}
