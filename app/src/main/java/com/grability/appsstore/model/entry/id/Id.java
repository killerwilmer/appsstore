
package com.grability.appsstore.model.entry.id;

import java.util.HashMap;
import java.util.Map;

public class Id {

    public String label;
    public Attributes attributes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
