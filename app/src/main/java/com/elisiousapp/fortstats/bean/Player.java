package com.elisiousapp.fortstats.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rafael Factum on 24/05/2018.
 */

public class Player{

    @SerializedName("Username")
    private String nome;
    @SerializedName("Kills")
    private int kills;
    @SerializedName("Matches Played")
    private int games;
    @SerializedName("Wins")
    private int wins;
    @SerializedName("K/d")
    private double kd;

    Player(String nome, int kills, int games, int wins, double kd) {
        this.nome = nome;
        this.kills = kills;
        this.games = games;
        this.wins = wins;
        this.kd = kd;
    }


    public String getNome() {
        return nome;
    }

    public int getKills() {
        return kills;
    }

    public int getGames() {
        return games;
    }

    public int getWins() {
        return wins;
    }

    public double getKd() {
        return kd;
    }
}
