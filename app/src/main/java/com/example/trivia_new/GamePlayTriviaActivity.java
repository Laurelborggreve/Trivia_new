package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia_new.R;

import java.util.ArrayList;

public class GamePlayTriviaActivity extends AppCompatActivity implements com.example.trivia.GamePlayTriviaRequest.Callback {
    String questionLevel;
    public int questionAmount;
    private com.example.trivia.GamePlayTriviaAdapter adapter;
    int scoreInt = 0;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_trivia);

        // Extract level and amount of questions
        Intent intent = getIntent();
        questionLevel = (String) intent.getSerializableExtra("questionLevel");
        questionAmount = (int) intent.getSerializableExtra("questionAmount");

        Log.d("questionLevel", questionLevel);

        com.example.trivia.GamePlayTriviaRequest gamePlayTriviaRequest = new com.example.trivia.GamePlayTriviaRequest(this);
        gamePlayTriviaRequest.getQuestions(this, questionLevel, questionAmount);
    }

    // Method to set adapter
    @Override
    public void gotQuestions(ArrayList<com.example.trivia.Question> questions) {
        adapter = new com.example.trivia.GamePlayTriviaAdapter(this, R.layout.adapter_game_play_trivia, questions);
        ListView listView = findViewById(R.id.list_questions);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //  Method to check the answer
    public void checkAnswer(View view) {
        String checkAnswer = view.getTag().toString();
        Log.d("correctAnswer", checkAnswer);
        if (checkAnswer.equals("correctAnswer")) {
            scoreInt++;
            score = String.valueOf(scoreInt);
        }
        else {
            scoreInt = scoreInt;
        }
    }

    // Method to set variables for new activity
    public void checkAnswers(View view) {
        Intent intent = new Intent(GamePlayTriviaActivity.this, com.example.trivia.HighScoresActivity.class);
        intent.putExtra("highScore", score);
        intent.putExtra("scoreInt", scoreInt);
        intent.putExtra("questionAmount", questionAmount);
        startActivity(intent);
    }
}

