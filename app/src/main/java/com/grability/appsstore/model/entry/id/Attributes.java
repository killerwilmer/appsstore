
package com.grability.appsstore.model.entry.id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Attributes {

    @SerializedName("im:id")
    @Expose
    public String im_id;

    @SerializedName("im:bundleId")
    @Expose
    public String im_bundleId;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
