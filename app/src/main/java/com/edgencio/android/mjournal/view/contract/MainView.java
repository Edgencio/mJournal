package com.edgencio.android.mjournal.view.contract;

import com.edgencio.android.mjournal.model.Entry;

/**
 * Created by edgencio on 6/30/18.
 */

public interface MainView {

    public void showProgress();
    public void hideProgress();
    public void showEntryClickedMessage(Entry entry);
    public void showError();
    public void openNewEntry();
}
