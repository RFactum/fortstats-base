package com.elisiousapp.fortstats.stats;

import com.elisiousapp.fortstats.bean.Player;
import com.elisiousapp.fortstats.webservice.RetrofitInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Rafael Factum on 25/05/2018.
 */

public class StatsPresenter implements StatsInterface.Presenter {

    StatsInterface.View view;

    Player player;
    Retrofit retrofit;

    @Inject
    StatsPresenter(StatsInterface.View view, Retrofit retrofit) {
        this.view = view;
        this.retrofit = retrofit;
    }


    @Override
    public void getUserInfo(String username) {
        if(!this.checkEmpty(username)) {
            retrofit.create(RetrofitInterface.class)
                    .getPlayerStats(username)
                    .enqueue(new Callback<Player>() {
                        @Override
                        public void onResponse(Call<Player> call, Response<Player> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    player = response.body();
                                    view.showStats(player);
                                } else {
                                    view.showNotFound();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Player> call, Throwable t) {
                            player = null;
                            view.showNotFound();
                        }
                    });
        } else {
            view.emptyMsg();
        }
    }

    public boolean checkEmpty(String str) {
        return str.trim().length() == 0;
    }
}
