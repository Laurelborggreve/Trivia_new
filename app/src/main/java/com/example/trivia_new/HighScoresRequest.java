package com.example.trivia;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighScoresRequest implements Response.Listener<String>, Response.ErrorListener {
    private Callback activity;
    private Context context;
    private String name, highScore;
    public ArrayList listHighScores;
    private JSONArray scoreboardInformation;


    public interface Callback {
        void gotHighScores(ArrayList<com.example.trivia.HighScore> listHighScores);
        void gotHighScoresError(String message);
    }

    public HighScoresRequest(Context cont, String highScore, String name) {
        context = cont;
        this.highScore = highScore;
        this.name = name;
    }

    public void postHighScore(HighScoresRequest.Callback activity) {
        this.activity = activity;
        String url = "https://ide50-laurelborggreve.legacy.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);

        // POST request
        com.example.trivia.PostRequest request = new com.example.trivia.PostRequest(Request.Method.POST, url, this, this, name, highScore);
        String listHighScores = "https://ide50-laurelborggreve.legacy.cs50.io:8080/list";

        // GET request
        Request requestHighScores = new StringRequest(Request.Method.GET, listHighScores, this, this);
        queue.add(request);
        queue.add(requestHighScores);
    }

    // Method that is called when something goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotHighScoresError(error.getMessage());
    }

    // Method that is called when everything goes as expected
    @Override
    public void onResponse(String response) {
        listHighScores = new ArrayList<com.example.trivia.HighScore>();
        try {
            scoreboardInformation = new JSONArray(response);
            for (int i = 0; i < scoreboardInformation.length(); i++) {
                JSONObject item = scoreboardInformation.getJSONObject(i);
                listHighScores.add(new com.example.trivia.HighScore(item.getString("highScore"), item.getString("name")));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        if (listHighScores.size() !=0 ) {
            activity.gotHighScores(listHighScores);
        }
    }
}
