package com.edgencio.android.mjournal.presenter.contract;

import com.edgencio.android.mjournal.model.Entry;

/**
 * Created by edgencio on 6/30/18.
 */

public interface NewEntryPresenter {


    public void saveEntry (Entry entry);
    public void updateEntry (Entry entry);
    public void deleteEntry(Entry entry);
}
