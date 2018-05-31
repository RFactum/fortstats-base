package com.elisiousapp.fortstats.data.component;

import com.elisiousapp.fortstats.data.module.PlayerModule;
import com.elisiousapp.fortstats.stats.StatsActivity;
import com.elisiousapp.fortstats.utils.ActivityScope;

import dagger.Component;

/**
 * Created by Rafael Factum on 25/05/2018.
 */

@ActivityScope
@Component(dependencies = {NetComponent.class},modules = {PlayerModule.class})
public interface PlayerComponent {

    void inject(StatsActivity statsActivity);
}
