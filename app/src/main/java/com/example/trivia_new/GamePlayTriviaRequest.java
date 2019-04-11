package com.example.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class GamePlayTriviaRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;
    public ArrayList<com.example.trivia.Question> listQuestions;
    public ArrayList<String> listIncorrectAnswers;
    private JSONArray arrayQuestions;

    // Method because data is not instantly returned
    public interface Callback {
        void gotQuestions(ArrayList<com.example.trivia.Question> questions);
        void gotQuestionsError(String message);
    }

    public GamePlayTriviaRequest(Context cont) {
        context = cont;
    }

    // Method to retrieve questions from the API
    public void getQuestions(Callback activity, String questionLevel, int questionAmount) {
        this.activity = activity;
        String url = "https://opentdb.com/api.php?" + "amount=" + questionAmount + "&category=10" + "&difficulty=" + questionLevel + "&type=multiple";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    // Method that is called when something goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotQuestionsError(error.getMessage());
    }

    // Method that is called when everything goes as expected
    @Override
    public void onResponse(JSONObject response) {
        Log.d("onRepsonse", response.toString());
        try {
            listQuestions = new ArrayList<>();
            arrayQuestions = response.getJSONArray("results");
            for (int i = 0; i < arrayQuestions.length(); i++) {
                JSONObject item = arrayQuestions.getJSONObject(i);
                String itemQuestion = item.getString("question");
                String difficulty = item.getString("difficulty");
                String correctAnswer = item.getString("correct_answer");
                JSONArray arrayIncorrectAnswers = item.getJSONArray("incorrect_answers");
                listIncorrectAnswers = new ArrayList<>();
                for (int j = 0; j < arrayIncorrectAnswers.length(); j++) {
                    listIncorrectAnswers.add(arrayIncorrectAnswers.getString(j));
                }
                com.example.trivia.Question question = new com.example.trivia.Question(itemQuestion, difficulty, correctAnswer, listIncorrectAnswers);
                listQuestions.add(question);
            }
            activity.gotQuestions(listQuestions);
        }
        catch (JSONException e) {
            Log.d("onResponse", e.toString());
            e.printStackTrace();
        }
    }
}
