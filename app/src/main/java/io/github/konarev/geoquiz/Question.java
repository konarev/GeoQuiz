package io.github.konarev.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mAnswerExist;
    private boolean mAnswer;

    public Question(int textResId, boolean answer) {
        mAnswer = answer;
        mTextResId = textResId;
        mAnswerExist = false;
    }

    public int getTextResId() {
        return mTextResId;
    }

/*
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }
*/

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswerExist() {
        return mAnswerExist;
    }

    public void setAnswerExist(boolean answerExist) {
        mAnswerExist = answerExist;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

/*
    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
*/

}
