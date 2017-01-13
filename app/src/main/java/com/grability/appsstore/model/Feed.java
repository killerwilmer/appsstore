
package com.grability.appsstore.model;

import com.grability.appsstore.model.entry.Entry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Feed {

    public Author author;
    public Updated updated;
    public Rights rights;
    public Title title;
    public Icon icon;
    public List<Link> link = null;
    public Id id;
    public List<Entry> entry = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
