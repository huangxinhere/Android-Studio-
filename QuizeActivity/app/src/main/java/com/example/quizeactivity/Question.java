package com.example.quizeactivity;

public class Question {
    private int mTestResId;
    private boolean mAnswerTrue;
    private boolean mAnswered;//用来标记是否完成回答，新增了一个实例

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

    public void setAnswered(boolean answered){//问题：answered从哪里来？？
        mAnswered = answered;
    }

    public boolean isAnswered(){
        return mAnswered;
    }

    public Question(int textResId, boolean answerTrue){
        mTestResId=textResId;
        mAnswerTrue=answerTrue;
    }

}
