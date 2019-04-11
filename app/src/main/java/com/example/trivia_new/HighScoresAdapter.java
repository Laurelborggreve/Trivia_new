package com.example.trivia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.trivia_new.R;

import java.util.ArrayList;
import java.util.Collections;

public class HighScoresAdapter extends ArrayAdapter<com.example.trivia.HighScore> {
    private ArrayList<com.example.trivia.HighScore> adapterHighScores;


    public HighScoresAdapter(Context context, int resource, ArrayList<com.example.trivia.HighScore> objects) {
        super(context, resource, objects);
        adapterHighScores = objects;
    }

    // Method to load the layout for each grid item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_high_scores, parent, false);
        }

        // Get access to layout's views
        TextView highScoreName = convertView.findViewById(R.id.high_score_name);
        highScoreName.setText(adapterHighScores.get(position).getHighScore());

        TextView highScoreScore = convertView.findViewById(R.id.high_score_score);
        highScoreScore.setText(adapterHighScores.get(position).getName());

        return convertView;
    }
}
