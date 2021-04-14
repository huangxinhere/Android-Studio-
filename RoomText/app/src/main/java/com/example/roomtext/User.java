package com.example.roomtext;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity//这个类是表
public class User {

    public User(@NonNull String first, String last){
        this.firstName = first;
        this.lastName = last;
    }
    //@NonNull: the parameter can never be null.
    public User(){

    }
    @PrimaryKey(autoGenerate = true)//生成id？
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return  "id = "+uid+
                ",first name = "+firstName+
                ",last name = "+lastName;
    }
}
