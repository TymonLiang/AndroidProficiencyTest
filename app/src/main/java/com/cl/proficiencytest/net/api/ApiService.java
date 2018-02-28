package com.cl.proficiencytest.net.api;
;

import com.cl.proficiencytest.model.Item;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiService {

    String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    @GET("facts.json")
    Observable<Item> getItem();


}