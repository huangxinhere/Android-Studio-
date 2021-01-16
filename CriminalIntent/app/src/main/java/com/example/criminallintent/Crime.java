package com.example.criminallintent;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;//Java工具类，有何用？
    private String mTitle;
    private Date mDate;
    private boolean mSolved;//什么意思来着？

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Crime(){
        mId = UUID.randomUUID();//产生一个随机唯一id值
        mDate = new Date();
    }
}
