package com.grability.appsstore.realm.adapters;

import android.content.Context;

import com.grability.appsstore.realm.model.App;

import io.realm.RealmResults;

/**
 * Created by killerwilmer on 17/01/17.
 */

public class RealmAppsAdaper extends RealmModelAdapter<App> {
    public RealmAppsAdaper(Context context, RealmResults<App> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}