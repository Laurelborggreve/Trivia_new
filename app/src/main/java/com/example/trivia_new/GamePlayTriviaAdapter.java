package com.example.trivia;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.trivia_new.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePlayTriviaAdapter extends ArrayAdapter<com.example.trivia.Question> {
    private ArrayList<com.example.trivia.Question> adapterQuestions;

    public GamePlayTriviaAdapter(Context context, int resource, ArrayList<com.example.trivia.Question> objects) {
        super(context, resource, objects);
        adapterQuestions = objects;
    }

    // Method to load the layout for each grid item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_game_play_trivia, parent, false);
        }

        // Get access to layout's views
        com.example.trivia.Question question = adapterQuestions.get(position);

        TextView textViewQuestion = convertView.findViewById(R.id.question);
        textViewQuestion.setText(Html.fromHtml(question.getQuestion()));

        RadioButton radioButtonCorrectAnswer = convertView.findViewById(R.id.correct_answer);
        RadioButton radioButtonIncorrectAnswer1 = convertView.findViewById(R.id.incorrect_answer_1);
        RadioButton radioButtonIncorrectAnswer2 = convertView.findViewById(R.id.incorrect_answer_2);
        RadioButton radioButtonIncorrectAnswer3 = convertView.findViewById(R.id.incorrect_answer_3);

        // Make list of all answers
        ArrayList<String> allAnswers = question.getIncorrectAnswers();
        String correctAnswer = question.getCorrectAnswer();
        allAnswers.add(correctAnswer);

        // Shuffle all answer
        Collections.shuffle(allAnswers);

        List<RadioButton> radioButtons = new ArrayList<>();

        // Set listview of radiobuttons
        radioButtons.add(radioButtonCorrectAnswer);convertView.findViewById(R.id.correct_answer);
        radioButtonCorrectAnswer.setTag("correctAnswer");
        radioButtons.add(radioButtonIncorrectAnswer1);convertView.findViewById(R.id.incorrect_answer_1);
        radioButtonIncorrectAnswer1.setTag("inCorrectAnswer");
        radioButtons.add(radioButtonIncorrectAnswer2);convertView.findViewById(R.id.incorrect_answer_2);
        radioButtonIncorrectAnswer2.setTag("inCorrectAnswer");
        radioButtons.add(radioButtonIncorrectAnswer3);convertView.findViewById(R.id.incorrect_answer_3);
        radioButtonIncorrectAnswer3.setTag("inCorrectAnswer");

        for (int i = 0; i < allAnswers.size(); i++) {
            radioButtons.get(i).setText(Html.fromHtml(allAnswers.get(i)).toString());
        }

        return convertView;
    }
}
