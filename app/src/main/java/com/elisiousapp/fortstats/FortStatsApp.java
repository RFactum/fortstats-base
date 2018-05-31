package com.elisiousapp.fortstats;

import android.app.Application;

import com.elisiousapp.fortstats.data.component.DaggerNetComponent;
import com.elisiousapp.fortstats.data.component.NetComponent;
import com.elisiousapp.fortstats.data.module.AppModule;
import com.elisiousapp.fortstats.data.module.NetModule;
import com.elisiousapp.fortstats.utils.ConfigUtils;

/**
 * Created by Rafael Factum on 22/05/2018.
 */

public class FortStatsApp extends Application{

    public static FortStatsApp instance;

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;

        netComponent = DaggerNetComponent.builder()
                        .appModule(new AppModule( (Application) instance ))
                // ADD THE API_URL TO THE CONFIGUTILS TO BE USED BY NETMODULE:
                        .netModule(new NetModule(ConfigUtils.API_URL))
                        .build();
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }
}
