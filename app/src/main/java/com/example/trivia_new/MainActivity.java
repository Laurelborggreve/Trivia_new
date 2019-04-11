package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.trivia_new.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, com.example.trivia.PreferencesActivity.class);
        startActivity(intent);
    }
}
