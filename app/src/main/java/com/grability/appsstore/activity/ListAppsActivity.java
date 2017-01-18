package com.grability.appsstore.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.grability.appsstore.R;
import com.grability.appsstore.realm.RealmController;
import com.grability.appsstore.realm.adapters.ListAppAdapter;
import com.grability.appsstore.realm.adapters.RealmAppsAdaper;
import com.grability.appsstore.realm.model.App;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListAppsActivity extends AppCompatActivity {

    private Realm realm;
    private ListAppAdapter adapter;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_apps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycler = (RecyclerView) findViewById(R.id.recyclerApps);
        setupRecycler();

        this.realm = RealmController.with(this).getRealm();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        Bundle bundle = getIntent().getExtras();
        String category_id = bundle.getString("category_id");
        Log.d("category_id", category_id);
        RealmResults<App> apps = RealmController.with(getApplication()).getAppsByCategory(category_id);
        setRealmAdapter(apps);

    }

    public void setRealmAdapter(RealmResults<App> apps) {

        RealmAppsAdaper realmAppsAdaper = new RealmAppsAdaper(this.getApplicationContext(), apps, true);
        adapter.setRealmAdapter(realmAppsAdaper);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        recycler.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapter = new ListAppAdapter(this);
        recycler.setAdapter(adapter);
    }

}
