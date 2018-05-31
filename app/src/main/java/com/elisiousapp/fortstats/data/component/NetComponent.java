package com.elisiousapp.fortstats.data.component;

import android.app.Application;

import com.elisiousapp.fortstats.data.module.AppModule;
import com.elisiousapp.fortstats.data.module.NetModule;
import com.elisiousapp.fortstats.stats.StatsActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Rafael Factum on 23/05/2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Application application();
    Retrofit retrofit();

}
