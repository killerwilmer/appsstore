package com.grability.appsstore.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;
import com.grability.appsstore.R;
import com.grability.appsstore.api.ApiApps;
import com.grability.appsstore.api.service.ServiceFactory;
import com.grability.appsstore.model.entry.Entry;
import com.grability.appsstore.realm.RealmController;
import com.grability.appsstore.realm.adapters.CategoriesAdapter;
import com.grability.appsstore.realm.adapters.RealmCategoriesAdaper;
import com.grability.appsstore.realm.model.Category;
import com.grability.appsstore.util.ActivityUtils;
import com.grability.appsstore.util.Constantes;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private CategoriesAdapter adapter;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycler = (RecyclerView) findViewById(R.id.recyclerCategories);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Constantes.REALM_NAME_DB)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        this.realm = RealmController.with(this).getRealm();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        if(isTablet(getApplicationContext())){
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        loadApps();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadApps() {

        boolean hasInternet = ActivityUtils.hasInternet(this);
        setupRecycler();

        if(hasInternet) {
            ApiApps service = ServiceFactory.createRetrofitService(ApiApps.class, Constantes.BASE_URL);
            service.getRssApps(Constantes.LIMIT, Constantes.FORMAT)
                    .concatMap(apps -> rx.Observable.from(apps.getFeed().getEntry()))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entry>() {
                        @Override
                        public final void onCompleted() {
                            RealmResults<Category> categories = RealmController.with(getApplication()).getCategoriesList();
                            setRealmAdapter(categories);
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("MainError", e.getMessage());
                        }

                        @Override
                        public final void onNext(Entry entry) {
                            RealmController.with(getApplication()).saveEntry(entry);
                        }
                    });
        } else {
            ActivityUtils.showDialog(this, "Información", "Conexión offline");
            RealmResults<Category> categories = RealmController.with(getApplication()).getCategoriesList();
            setRealmAdapter(categories);
        }
    }

    public void setRealmAdapter(RealmResults<Category> categories) {

        RealmCategoriesAdaper realmAdapter = new RealmCategoriesAdaper(this.getApplicationContext(), categories, true);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        recycler.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapter = new CategoriesAdapter(this);
        recycler.setAdapter(adapter);
    }

    public static boolean isTablet(Context context) {

        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;

    }
}
