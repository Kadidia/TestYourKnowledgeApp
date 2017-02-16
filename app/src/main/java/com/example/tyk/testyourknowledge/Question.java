package com.example.tyk.testyourknowledge;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by shegd on 14/02/2017.
 */

public class Question implements Parcelable {
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

    protected Question(Parcel in) {
        question = in.readString();
        response = in.readString();
        choices = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Question> QUESTION_CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>(){
        @Override
        public Question createFromParcel(Parcel source){
            return  new Question(source);
        }
        @Override
        public Question[] newArray(int size){
            return new Question[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(question);
        parcel.writeString(response);
        parcel.writeStringList(choices);

    }
}
