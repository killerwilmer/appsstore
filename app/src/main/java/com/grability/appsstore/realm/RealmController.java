package com.grability.appsstore.realm;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.grability.appsstore.model.entry.Entry;
import com.grability.appsstore.model.entry.imimage.ImImage;
import com.grability.appsstore.realm.model.App;
import com.grability.appsstore.realm.model.Category;
import com.grability.appsstore.realm.model.Imagen;
import com.grability.appsstore.util.Constantes;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by killerwilmer on 13/01/17.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public void refresh() {
        realm.refresh();
    }


    public App getAppById(String id) {
        return realm.where(App.class).equalTo("id", id).findFirst();
    }

    public boolean hasCategories() {
        return !realm.allObjects(Category.class).isEmpty();
    }

    public RealmResults<Category> queryCategories() {
        return realm.where(Category.class)
                .contains("term", "Utilities")
                .or()
                .contains("label", "Utilities")
                .findAll();
    }

    public void saveEntry(Entry entry) {

        App app = new App();
        app.setId(entry.getId().getAttributes().getIm_id());
        app.setIm_name(entry.getIm_name().getLabel());
        app.setSumary(entry.getSummary().getLabel());
        app.setPrice(entry.getIm_price().getAttributes().getAmount());
        app.setContentType(entry.getIm_contentType().getAttributes().getTerm());
        app.setRights(entry.getRights().getLabel());
        app.setTitle(entry.getTitle().getLabel());
        app.setLink(entry.getLink().getAttributes().getHref());
        app.setArtist(entry.getIm_artist().getLabel());
        app.setReleaseDate(entry.getIm_releaseDate().getLabel());
        app.setImages(new RealmList<>());

        Category category = new Category();
        category.setId(entry.getCategory().getAttributes().getIm_id());
        category.setLabel(entry.getCategory().getAttributes().getLabel());
        category.setScheme(entry.getCategory().getAttributes().getScheme());
        category.setTerm(entry.getCategory().getAttributes().getTerm());

        for (ImImage imImage : entry.getIm_image()) {
            Imagen image = new Imagen();
            image.setId(entry.getId().getAttributes().getIm_id() + "-" + imImage.getAttributes().getHeight());
            image.setLabel(imImage.getLabel());
            image.setHeight(imImage.getAttributes().getHeight());
            app.getImages().add(image);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(image);
            realm.commitTransaction();
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(category);
        app.setCategory(category);
        realm.copyToRealmOrUpdate(app);
        realm.commitTransaction();
    }

    public RealmResults<Category> getCategoriesList() {
        return realm.where(Category.class).findAllSorted(Constantes.CATEGORY_NAME);
    }

    public RealmResults<App> getAppsByCategory(String categoryId) {
        return realm.where(App.class).equalTo("category.id", categoryId).findAllSorted("im_name");
    }
}
