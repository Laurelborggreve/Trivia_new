package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.trivia_new.R;

public class PreferencesActivity extends AppCompatActivity {

    // Declare variables
    public String questionLevel;
    public int questionAmount;
    public int questionButtonSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }

    // Extract which level of difficulty is selected
    public void questionLevelClicked(View view) {
        RadioGroup questionLevelButtons = findViewById(R.id.question_level_all);
        questionButtonSelected = questionLevelButtons.getCheckedRadioButtonId();
        RadioButton questionLevelSelected = findViewById(questionButtonSelected);
        questionLevel = questionLevelSelected.getText().toString();

        // Show user which level is selected
        Toast.makeText(getApplicationContext(), questionLevel + " is selected", Toast.LENGTH_SHORT).show();
    }

    // Extract amount of questions entered and change string to integer
    public void getQuestionsAmount () {
        // EXTRA FOR IMPROVING PROJECT: The EditText ensures that only integer can be entered as input
        EditText amountOfQuestions = findViewById(R.id.amount_of_questions);
        String amountOfQuestionsInput = amountOfQuestions.getText().toString();
        questionAmount = new Integer(amountOfQuestionsInput).intValue();
    }

    public void startGameButtonClicked(View view) {
        getQuestionsAmount();

        // Check if difficulty level of questions is selected
        if (questionButtonSelected == 0) {
            Toast.makeText(getApplicationContext(), "You have to choose a level of difficulty", Toast.LENGTH_LONG).show();
        }
        // Check if right number of questions is filled in
        else if (questionAmount < 1 || questionAmount > 20) {
            Toast.makeText(getApplicationContext(), "The amount of questions must be between 1 and 20", Toast.LENGTH_LONG).show();
        }
        else {
            // Start the Trivia Game
            Intent intent = new Intent(PreferencesActivity.this, com.example.trivia.GamePlayTriviaActivity.class);
            intent.putExtra("questionLevel", questionLevel);
            intent.putExtra("questionAmount", questionAmount);
            startActivity(intent);
        }
    }
}


