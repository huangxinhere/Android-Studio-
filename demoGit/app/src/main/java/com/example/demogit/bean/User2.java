package com.example.demogit.bean;

public class User2 {
    private String name;
    private String word;

    public User2(String name, String word) {
        this.name = name;
        this.word = word;
    }
    public User2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
