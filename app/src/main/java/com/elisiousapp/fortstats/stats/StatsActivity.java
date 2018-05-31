package com.elisiousapp.fortstats.stats;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elisiousapp.fortstats.FortStatsApp;
import com.elisiousapp.fortstats.R;
import com.elisiousapp.fortstats.bean.Player;
import com.elisiousapp.fortstats.data.component.DaggerPlayerComponent;
import com.elisiousapp.fortstats.data.module.PlayerModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class StatsActivity extends AppCompatActivity implements StatsInterface.View {

    @Inject
    StatsPresenter presenter;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.stats_waiting)
    TextView statsWaiting;
    @BindView(R.id.btn_stats_search)
    Button btnStatsSearch;
    @BindView(R.id.stats_username)
    TextView statsUsername;
    @BindView(R.id.stats_kills)
    TextView statsKills;
    @BindView(R.id.stats_wins)
    TextView statsWins;
    @BindView(R.id.stats_games)
    TextView statsGames;
    @BindView(R.id.stats_kd)
    TextView statsKd;

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.ll_stats_show)
    LinearLayout llStatsShow;
    @BindView(R.id.stats_progress_bar)
    ProgressBar statsProgressBar;
    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ButterKnife.bind(this);

        configToolbar();
        loadDagger();

    }

    private void configToolbar() {
        toolbar.setTitle(R.string.stats_title);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
    }

    private void loadDagger() {
        DaggerPlayerComponent.builder()
                .netComponent(((FortStatsApp) getApplicationContext()).getNetComponent())
                .playerModule(new PlayerModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.btn_stats_search)
    public void onViewClicked() {
        statsProgressBar.setVisibility(View.VISIBLE);
        llStatsShow.setVisibility(View.GONE);
        statsWaiting.setVisibility(View.GONE);
        hideKeyboard();

        presenter.getUserInfo(edtUsername.getText().toString());
    }

    @OnTextChanged(R.id.edt_username)
    public void cleanError() {
        textInputLayout.setError(null);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showStats(Player player) {
        statsUsername.setText(player.getNome());
        statsKills.setText("Kills : " + player.getKills());
        statsGames.setText("Matches Played: " + player.getGames());
        statsWins.setText("Wins: " + player.getWins());
        statsKd.setText("K/D: " + player.getKd());

        llStatsShow.setVisibility(View.VISIBLE);
        statsProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNotFound() {
        statsWaiting.setText(R.string.stats_user_not_found);
        statsWaiting.setVisibility(View.VISIBLE);
        statsProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void emptyMsg() {
        statsProgressBar.setVisibility(View.GONE);
        statsWaiting.setText(R.string.stats_watiing_username);
        statsWaiting.setVisibility(View.VISIBLE);
        textInputLayout.setError(getString(R.string.stats_empty_username));
    }
}
