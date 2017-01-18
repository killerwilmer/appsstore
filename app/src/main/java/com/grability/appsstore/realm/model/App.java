package com.grability.appsstore.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by killerwilmer on 16/01/17.
 */

public class App extends RealmObject {
    @PrimaryKey
    private String id;
    private String im_name;
    private RealmList<Imagen> images;
    private String sumary;
    private String price;
    private String contentType;
    private String rights;
    private String title;
    private String link;
    private String artist;
    private Category category;
    private String releaseDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIm_name() {
        return im_name;
    }

    public void setIm_name(String im_name) {
        this.im_name = im_name;
    }

    public RealmList<Imagen> getImages() {
        return images;
    }

    public void setImages(RealmList<Imagen> images) {
        this.images = images;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
