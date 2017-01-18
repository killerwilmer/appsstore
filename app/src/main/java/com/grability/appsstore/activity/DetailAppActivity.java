package com.grability.appsstore.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.grability.appsstore.R;
import com.grability.appsstore.realm.RealmController;
import com.grability.appsstore.realm.adapters.ListAppAdapter;
import com.grability.appsstore.realm.model.App;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class DetailAppActivity extends AppCompatActivity {

    private Realm realm;
    private ListAppAdapter adapter;
    private TextView txtNameApp;
    private TextView txtSummary;
    private ImageView imgApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.realm = RealmController.with(this).getRealm();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        Bundle bundle = getIntent().getExtras();
        String app_id = bundle.getString("app_id");
        Log.d("app_id", app_id);
        App app = RealmController.with(getApplication()).getAppById(app_id);
        loadDetailInfo(app);
    }

    public void loadDetailInfo(App app) {
        txtNameApp = (TextView) findViewById(R.id.txt_name_app);
        txtSummary = (TextView) findViewById(R.id.txt_summary);
        imgApp = (ImageView) findViewById(R.id.img_app);


        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(2000);

        // Start animating the image
        imgApp.startAnimation(anim);


        txtNameApp.setText(app.getIm_name());
        Picasso.with(getApplicationContext()).load(app.getImages().get(2).getLabel()).into(imgApp);
        txtSummary.setText(app.getSumary());

        // Later.. stop the animation
        imgApp.setAnimation(null);
    }

}
