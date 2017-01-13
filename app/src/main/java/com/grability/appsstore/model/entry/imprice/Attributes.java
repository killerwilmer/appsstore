
package com.grability.appsstore.model.entry.imprice;

import java.util.HashMap;
import java.util.Map;

public class Attributes {

    public String amount;
    public String currency;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
