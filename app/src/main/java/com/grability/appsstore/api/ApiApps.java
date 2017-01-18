package com.grability.appsstore.api;


import com.grability.appsstore.model.Apps;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by killerwilmer on 11/01/17.
 */

public interface ApiApps {
    @GET("limit={limit}/{format}")
    Observable<Apps> getRssApps(@Path("limit") String limit, @Path("format") String format);

}
