package com.grability.appsstore.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.grability.appsstore.R;
import com.grability.appsstore.api.ApiApps;
import com.grability.appsstore.api.service.ServiceFactory;
import com.grability.appsstore.model.Apps;
import com.grability.appsstore.model.entry.Entry;
import com.grability.appsstore.util.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(isTablet(getApplicationContext())){
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        loadApps2();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadApps() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiApps apiApps = retrofit.create(ApiApps.class);

        Call<Apps> call = apiApps.getApps(Constantes.LIMIT, Constantes.FORMAT);

        call.enqueue(new Callback<Apps>() {


            @Override
            public void onResponse(Call<Apps> call, Response<Apps> response) {
                Log.d("Apps", response.body().toString());
            }

            @Override
            public void onFailure(Call<Apps> call, Throwable t) {
                Log.e("error1",t.getMessage());
            }
        });

    }

    public void loadApps2() {

        ApiApps service = ServiceFactory.createRetrofitService(ApiApps.class, Constantes.BASE_URL);
            service.getRssApps(Constantes.LIMIT, Constantes.FORMAT)
                    .concatMap(apps -> rx.Observable.from(apps.getFeed().getEntry()))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Entry>() {
                        @Override
                        public final void onCompleted() {
                            //Mostrar las categorias desde Realm
                        }

                        @Override
                        public final void onError(Throwable e) {
                            Log.e("GithubDemo", e.getMessage());
                        }

                        @Override
                        public final void onNext(Entry entry) {
                            Log.d("Main", entry.toString());
                            //Guardar la nueva instancia en Realm
                        }
                    });
    }

    public static boolean isTablet(Context context) {

        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;

    }// isTablet
}
