package com.edgencio.android.mjournal.presenter;

import android.content.Context;

import com.edgencio.android.mjournal.model.Entry;
import com.edgencio.android.mjournal.model.service.EntryService;
import com.edgencio.android.mjournal.presenter.contract.MainPresenter;
import com.edgencio.android.mjournal.view.contract.MainView;

import java.util.List;

/**
 * Created by edgencio on 6/30/18.
 */

public class MainPresenterImpl implements MainPresenter {
    private final MainView mView;
    EntryService entryService;

    public MainPresenterImpl(MainView view, Context context){
        mView = view;
        entryService = new EntryService(context);
    }

    @Override
    public List<Entry> loadEntries() {
    return entryService.retrieveEntries();
    }

    @Override
    public void clickEntryItem(Entry entry) {
    mView.showEntryClickedMessage(entry);
    }
}
