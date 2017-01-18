
package com.grability.appsstore.model.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.grability.appsstore.model.entry.category.Category;
import com.grability.appsstore.model.entry.id.Id;
import com.grability.appsstore.model.entry.imartist.ImArtist;
import com.grability.appsstore.model.entry.imcontenttype.ImContentType;
import com.grability.appsstore.model.entry.imimage.ImImage;
import com.grability.appsstore.model.entry.imprice.ImPrice;
import com.grability.appsstore.model.entry.imreleasedate.ImReleaseDate;
import com.grability.appsstore.model.entry.link.Link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entry {

    @SerializedName("im:name")
    @Expose
    private ImName im_name;

    @SerializedName("im:image")
    @Expose
    private List<ImImage> im_image = new ArrayList();

    public Summary summary;

    @SerializedName("im:price")
    @Expose
    private ImPrice im_price;

    @SerializedName("im:contentType")
    @Expose
    private ImContentType im_contentType;

    private Rights rights;
    private Title title;
    private Link link;
    private Id id;

    @SerializedName("im:artist")
    @Expose
    private ImArtist im_artist;

    private Category category;

    @SerializedName("im:releaseDate")
    @Expose
    private ImReleaseDate im_releaseDate;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ImName getIm_name() {
        return im_name;
    }

    public void setIm_name(ImName im_name) {
        this.im_name = im_name;
    }

    public List<ImImage> getIm_image() {
        return im_image;
    }

    public void setIm_image(List<ImImage> im_image) {
        this.im_image = im_image;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public ImPrice getIm_price() {
        return im_price;
    }

    public void setIm_price(ImPrice im_price) {
        this.im_price = im_price;
    }

    public ImContentType getIm_contentType() {
        return im_contentType;
    }

    public void setIm_contentType(ImContentType im_contentType) {
        this.im_contentType = im_contentType;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public ImArtist getIm_artist() {
        return im_artist;
    }

    public void setIm_artist(ImArtist im_artist) {
        this.im_artist = im_artist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ImReleaseDate getIm_releaseDate() {
        return im_releaseDate;
    }

    public void setIm_releaseDate(ImReleaseDate im_releaseDate) {
        this.im_releaseDate = im_releaseDate;
    }
}
