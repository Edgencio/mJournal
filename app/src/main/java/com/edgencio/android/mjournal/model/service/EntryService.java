package com.edgencio.android.mjournal.model.service;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.edgencio.android.mjournal.model.DAO.AppDatabase;
import com.edgencio.android.mjournal.model.DAO.EntryDAO;
import com.edgencio.android.mjournal.model.Entry;

import java.util.List;

/**
 * Created by edgencio on 6/30/18.
 */

public class EntryService {

    AppDatabase database;
    EntryDAO entryDao;

    public EntryService(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "db-entries")
                .allowMainThreadQueries()
                .build();
        entryDao = database.getEntryDAO();

    }

    public void saveEntry(Entry entry){
        entryDao.insert(entry);
    }

    public List<Entry> retrieveEntries(){
        return entryDao.getEntries();
    }


    public List<Entry> retrieveArquivedEntries(){
        return entryDao.getArquivedEntries();
    }

    public List<Entry> retrieveEntry(Entry entry){
        return entryDao.getEntryById(entry.getId());
    }

    public void DeleteEntry(Entry entry){
        entryDao.delete(entry);
    }

    public void UpdateEntry(Entry entry){
        entryDao.delete(entry);
    }
}
