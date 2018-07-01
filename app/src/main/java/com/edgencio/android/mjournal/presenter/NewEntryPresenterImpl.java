package com.edgencio.android.mjournal.presenter;

import android.content.Context;

import com.edgencio.android.mjournal.model.Entry;
import com.edgencio.android.mjournal.model.service.EntryService;
import com.edgencio.android.mjournal.presenter.contract.NewEntryPresenter;

/**
 * Created by edgencio on 6/30/18.
 */

public class NewEntryPresenterImpl implements NewEntryPresenter {

    EntryService entryService;

    public NewEntryPresenterImpl(Context context){
        entryService = new EntryService(context);
    }

    @Override
    public void saveEntry(Entry entry) {
       entryService.saveEntry(entry);

    }

    @Override
    public void updateEntry(Entry entry) {

    }

    @Override
    public void deleteEntry(Entry entry) {

    }
}
