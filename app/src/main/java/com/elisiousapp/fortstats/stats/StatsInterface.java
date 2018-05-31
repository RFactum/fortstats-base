package com.elisiousapp.fortstats.stats;

import com.elisiousapp.fortstats.bean.Player;

/**
 * Created by Rafael Factum on 24/05/2018.
 */

public interface StatsInterface {

    interface View {
        void showStats(Player player);
        void showNotFound();
        void emptyMsg();

    }

    interface Presenter {
        void getUserInfo(String username);
        boolean checkEmpty(String str);
    }

}
