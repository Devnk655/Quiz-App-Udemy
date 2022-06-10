package com.example.quizappudemy;

public class QuizModel {
    private int mQuestions;
    private boolean mAnswers;
    public QuizModel(int question,boolean answer){
        mQuestions=question;
        mAnswers=answer;

    }

    public int getmQuestions() {
        return mQuestions;
    }

    public void setmQuestions(int mQuestions) {
        this.mQuestions = mQuestions;
    }

    public boolean ismAnswers() {
        return mAnswers;
    }

    public void setmAnswers(boolean mAnswers) {
        this.mAnswers = mAnswers;
    }
}
