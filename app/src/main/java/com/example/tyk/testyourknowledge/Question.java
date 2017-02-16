package com.example.tyk.testyourknowledge;

import java.util.List;

/**
 * Created by shegd on 14/02/2017.
 */

public class Question {
    private String question;
    private String response;
    private List<String> choices;


    public Question() {
    }

    public Question(String question, String response, List<String> choices) {
        this.question = question;
        this.response = response;
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
