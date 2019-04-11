package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia_new.R;

import java.util.ArrayList;

public class HighScoresActivity extends AppCompatActivity implements com.example.trivia.HighScoresRequest.Callback {
    private ArrayList listHighScoresAll;
    private com.example.trivia.HighScoresAdapter adapter;
    private String highScore;
    public int highScoreInt;
    int questionAmount;
    private Button userEnterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        userEnterName = findViewById(R.id.user_enter_name);

        // Retrieve values from intent
        Intent intent = getIntent();
        highScore = (String) intent.getSerializableExtra("highScore");
        highScoreInt = (int) intent.getSerializableExtra("scoreInt");

        questionAmount = (int) intent.getSerializableExtra("questionAmount");
        listHighScoresAll = new ArrayList<com.example.trivia.HighScore>();

        // Let user know his score
        TextView textView = findViewById(R.id.user_high_score);
        textView.setText("Your score is: " + highScoreInt + " out of " + questionAmount);

        // Check if user has clicked to save its score
        if(savedInstanceState != null){
            userEnterName.setEnabled(savedInstanceState.getBoolean("disableUserEnterName"));
            listHighScoresAll = savedInstanceState.getParcelableArrayList("listHighScoresAll");
            if(listHighScoresAll.size() != 0) gotHighScores(listHighScoresAll);
        }
    }

    @Override
    public void gotHighScores(ArrayList<com.example.trivia.HighScore> listHighScores) {
        listHighScoresAll = listHighScores;
        adapter = new com.example.trivia.HighScoresAdapter(this, R.layout.activity_high_scores, listHighScores);
        ListView listView = findViewById(R.id.list_high_scores);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotHighScoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Method to save layout
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("disableUserEnterName", userEnterName.isEnabled());
        outState.putParcelableArrayList("listHighScoresAll", listHighScoresAll);
    }

    public void enterNameClicked(View view) {
        Log.d("hstest", "enterNameClicked: "+ highScore);
        EditText userName = findViewById(R.id.user_name);
        com.example.trivia.HighScoresRequest highScoresRequest = new com.example.trivia.HighScoresRequest(this, highScore, userName.getText().toString());
        highScoresRequest.postHighScore(this);

        // Ensure you can only enter your name once
        userEnterName = (Button) view;
        userEnterName.setEnabled(false);
    }
}
