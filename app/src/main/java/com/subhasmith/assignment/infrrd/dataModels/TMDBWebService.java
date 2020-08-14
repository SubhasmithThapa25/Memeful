package com.subhasmith.assignment.infrrd.dataModels;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Subhasmith
 */

public interface TMDBWebService {

    /**
     * Get a list of images via ImGur API .
     */
    @GET("/3/gallery/top/defaults/day/{page}")
    Call<ResponseBody> getImgurData(@Path("page") int page,
                                    @Query("showViral") boolean showViral,
                                    @Query("mature") boolean mature,
                                    @Query("album_previews") boolean albumPreviews);
}
