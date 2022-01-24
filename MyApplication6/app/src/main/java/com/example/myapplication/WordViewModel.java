package com.example.myapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    WordRepository wordRepository;
    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    void insertWord(Word... words){
        wordRepository.insertWord(words);
    }
    void deleteWord(Word... words){
        wordRepository.deleteWord(words);
    }
    void updateWord(Word... words){
        wordRepository.updateWord(words);
    }
    void clearWord(){
        wordRepository.clearWord();
    }


    public LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }
}
