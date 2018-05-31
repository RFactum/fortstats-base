package com.elisiousapp.fortstats.utils;

import com.elisiousapp.fortstats.bean.Player;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;

/**
 * Created by Rafael Factum on 25/05/2018.
 */

public class MyDeserializer implements JsonDeserializer<Player> {


    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement playerFull = json.getAsJsonObject().get("lifeTimeStats");
        String playerStr = (new Gson()).toJson(playerFull);
        try {
            JSONArray playerArray = new JSONArray(playerStr);
            JsonObject player = new JsonObject ();
            player.addProperty("Username", json.getAsJsonObject().get("epicUserHandle").getAsString());
            player.addProperty("Kills", playerArray.getJSONObject(10).getString("value"));
            player.addProperty("Wins", playerArray.getJSONObject(8).getString("value"));
            player.addProperty("Matches Played", playerArray.getJSONObject(7).getString("value"));
            player.addProperty("K/d", playerArray.getJSONObject(11).getString("value"));
            return new Gson().fromJson(player, Player.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
