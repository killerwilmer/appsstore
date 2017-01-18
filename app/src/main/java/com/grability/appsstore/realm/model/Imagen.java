package com.grability.appsstore.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by killerwilmer on 16/01/17.
 */

public class Imagen extends RealmObject {
    @PrimaryKey
    private String id;
    private String label;
    private String height;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
