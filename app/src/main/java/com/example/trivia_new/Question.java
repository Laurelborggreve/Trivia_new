package com.example.trivia;

import java.io.Serializable;
import java.util.ArrayList;

public class Question {
    String question;
    String difficulty;
    String correctAnswer;
    private ArrayList<String> incorrectAnswers;

    public Question(String question, String difficulty, String correctAnswer, ArrayList<String> incorrectAnswers) {
        this.question = question;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
