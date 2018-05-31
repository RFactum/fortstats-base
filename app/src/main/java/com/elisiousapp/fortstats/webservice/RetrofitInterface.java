package com.elisiousapp.fortstats.webservice;

import com.elisiousapp.fortstats.bean.Player;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Rafael Factum on 24/05/2018.
 */

public interface RetrofitInterface {

    @GET("pc/{user}")
    Call<Player> getPlayerStats(@Path("user") String user);
}
