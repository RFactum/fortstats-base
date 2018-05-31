package com.elisiousapp.fortstats.data.module;

import com.elisiousapp.fortstats.stats.StatsInterface;
import com.elisiousapp.fortstats.utils.ActivityScope;


import dagger.Module;
import dagger.Provides;

/**
 * Created by Rafael Factum on 25/05/2018.
 */

@Module
public class PlayerModule {

    StatsInterface.View view;

    public PlayerModule(StatsInterface.View view) {
        this.view = view;
    }


    @ActivityScope
    @Provides
    public StatsInterface.View provideStatsInterfaceView() {
        return view;
    }

}
