package com.edgencio.android.mjournal.view;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edgencio.android.mjournal.R;
import com.edgencio.android.mjournal.model.Entry;
import com.edgencio.android.mjournal.presenter.NewEntryPresenterImpl;

import java.util.Date;

public class NewEntryActivity extends AppCompatActivity {

    NewEntryPresenterImpl entryImplementation;

    Button btnSave;
    EditText edtTopic, edtDescription;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_title_new_entry);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        entryImplementation = new NewEntryPresenterImpl(this);

        btnSave = (Button) findViewById(R.id.btn_save);
        edtTopic = (EditText) findViewById(R.id.edt_topic);
        edtDescription = (EditText) findViewById(R.id.edt_description);

        Bundle bundle = getIntent().getExtras();

        String topic = "";
        String description = "";
        if (bundle != null) {
            topic = bundle.getString("username");
            description = bundle.getString("email");
            id = bundle.getInt("id");

            edtTopic.setText(topic);
            edtDescription.setText(description);

        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Entry entry = new Entry();
                entry.setCreatedAt(new Date());
                entry.setIsArquived(0);
                entry.setTopic(edtTopic.getText().toString());
                entry.setUpdatedAt(new Date());
                entry.setScheduledTo(new Date());
                entry.setDescription(edtDescription.getText().toString());

                if (id == 0) {
                    entryImplementation.saveEntry(entry);
                } else {
                    entryImplementation.updateEntry(entry);
                }
                Toast.makeText(v.getContext(), "Entry Successful created", Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
