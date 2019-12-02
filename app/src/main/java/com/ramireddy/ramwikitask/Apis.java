package com.ramireddy.ramwikitask;

import com.ramireddy.ramwikitask.model.WikiModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apis {

    @GET("api.php?action=query&format=json&prop=info%7Cdescription%7Cpageimages&generator=prefixsearch&redirects=1&formatversion=2&inprop=url&piprop=thumbnail&pithumbsize=50&gpssearch=android")
    Call<WikiModel> getQuery();
}
