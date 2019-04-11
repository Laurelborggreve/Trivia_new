package com.example.trivia;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/*
    Objects of this class can do POST requests with parameters.
*/
public class PostRequest extends StringRequest {
    private String name, highScore;

    // Constructor
    public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String name, String highScore) {
        super(method, url, listener, errorListener);
        this.name = name;
        this.highScore = highScore;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("highScore", highScore);
        return params;
    }
}
