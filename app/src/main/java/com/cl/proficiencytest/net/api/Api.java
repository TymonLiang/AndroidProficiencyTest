package com.cl.proficiencytest.net.api;

import com.cl.proficiencytest.net.BaseApiImpl;

public class Api extends BaseApiImpl {

    private static Api api = new Api(ApiService.BASE_URL);

    public Api(String baseUrl) {
        super(baseUrl);
    }

    public static ApiService getInstance() {
        return api.getRetrofit().create(ApiService.class);
    }
}
