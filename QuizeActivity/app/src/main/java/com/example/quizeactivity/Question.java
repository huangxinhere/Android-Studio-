package com.example.quizeactivity;

public class Question {
    private int mTestResId;
    private boolean mAnswerTrue;

    public boolean isAnswerTrue() {
        return mAnswerTrue;//和下面方法的区别set；set：接受值赋予给实例//is就是返回本身
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTestResId() {
        return mTestResId;
    }

    public void setTestResId(int testResId) {//为什么要用get，set？
        mTestResId = testResId;
    }

    public Question(int textResId, boolean answerTrue){
        mTestResId=textResId;
        mAnswerTrue=answerTrue;
    }
}
