package com.grability.appsstore.realm.adapters;

import android.content.Context;

import com.grability.appsstore.realm.model.Category;

import io.realm.RealmResults;

/**
 * Created by killerwilmer on 13/01/17.
 */

public class RealmCategoriesAdaper extends RealmModelAdapter<Category> {
    public RealmCategoriesAdaper(Context context, RealmResults<Category> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
