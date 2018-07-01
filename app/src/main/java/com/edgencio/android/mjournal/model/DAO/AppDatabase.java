package com.edgencio.android.mjournal.model.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.edgencio.android.mjournal.model.DAO.util.DateTypeConverter;
import com.edgencio.android.mjournal.model.Entry;

/**
 * Created by edgencio on 6/30/18.
 */

@Database(entities = {Entry.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract EntryDAO getEntryDAO();
}