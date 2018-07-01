package com.edgencio.android.mjournal.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.edgencio.android.mjournal.model.Entry;

import java.util.List;

/**
 * Created by edgencio on 6/30/18.
 */

@Dao
public interface EntryDAO {

    @Insert
    public void insert(Entry... entries);

    @Update
    public void update(Entry... update);

    @Delete
    public void delete(Entry... entries);

    @Query("SELECT * FROM entries")
    public List<Entry> getEntries();


    @Query("SELECT * FROM entries WHERE isArquived = 1")
    public List<Entry> getArquivedEntries();

    @Query("SELECT * FROM entries WHERE id = :id")
    public List<Entry> getEntryById(int id);
}
