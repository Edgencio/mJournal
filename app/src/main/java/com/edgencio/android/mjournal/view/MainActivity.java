package com.edgencio.android.mjournal.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.edgencio.android.mjournal.R;
import com.edgencio.android.mjournal.adapter.EntryListAdapter;
import com.edgencio.android.mjournal.model.Entry;
import com.edgencio.android.mjournal.presenter.MainPresenterImpl;
import com.edgencio.android.mjournal.view.contract.MainView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView, EntryListAdapter.EntryItemListener {

    private MainPresenterImpl mPresenter;

    private EntryListAdapter mEntryListAdapter;
    private View headerView;
    private TextView ttvUser, ttvMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_title_my_entries);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewEntry();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle bundle = getIntent().getExtras();

        String username = "";
        String email = "";
        if (bundle != null) {
            username = bundle.getString("username");
            email = bundle.getString("email");

        }
        headerView = navigationView.getHeaderView(0);
        ttvUser = (TextView) headerView.findViewById(R.id.ttv_nav_header_username);
        ttvMail = (TextView) headerView.findViewById(R.id.ttv_nav_header_mail);

        ttvMail.setText(email);
        ttvUser.setText(username);

        mPresenter = new MainPresenterImpl(this, this);

        initRecyclerView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            showEntries();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_entries) {
            // Handle the camera action
        } else if (id == R.id.nav_arquive) {

        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_share) {
            share();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initRecyclerView() {
        mEntryListAdapter = new EntryListAdapter(this, this);

        RecyclerView rvWeatherList = (RecyclerView) findViewById(R.id.rview_main);
        rvWeatherList.setLayoutManager(new LinearLayoutManager(this));
        rvWeatherList.setAdapter(mEntryListAdapter);
        showEntries();
    }


    public void showEntries() {
        List<Entry> entries = new ArrayList();
        entries = mPresenter.loadEntries();
        if (entries != null) {
            mEntryListAdapter.replaceData(entries);
        }


    }

    @Override
    public void onEntryItemClick(Entry item) {
        mPresenter.clickEntryItem(item);

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showEntryClickedMessage(Entry entry) {

        Log.e("WORKS", "works");

        Bundle bundle= new Bundle();
        bundle.putInt("id",entry.getId());
        bundle.putString("topic", entry.getTopic());
        bundle.putString("description", entry.getDescription());
        Intent intent = new Intent(this, NewEntryActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void showError() {

    }

    @Override
    public void openNewEntry() {
        Intent intent = new Intent(this, NewEntryActivity.class);
        startActivity(intent);
    }

    public void share(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody =getString(R.string.txt_menu_share);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.menu_share)));
    }

}
