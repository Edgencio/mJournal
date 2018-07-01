package com.edgencio.android.mjournal.presenter.contract;

import com.edgencio.android.mjournal.model.Entry;

import java.util.List;

/**
 * Created by edgencio on 6/30/18.
 */

public interface MainPresenter {

    List<Entry> loadEntries();
    void clickEntryItem(Entry entry);
}
