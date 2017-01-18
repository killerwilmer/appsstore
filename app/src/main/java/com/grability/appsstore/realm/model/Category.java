package com.grability.appsstore.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by killerwilmer on 13/01/17.
 */

public class Category extends RealmObject {
    @PrimaryKey
    private String id;
    private String term;
    private String scheme;
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
