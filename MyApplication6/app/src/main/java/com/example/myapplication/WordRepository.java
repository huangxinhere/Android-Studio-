package com.example.myapplication;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    List<Word> allWordsLive;
    WordDao wordDao;

    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getInstance(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordsLive = wordDao.getAllWords();
    }

/*methods*/
    LiveData<List<Word>> getAllWordsLive(){
        return (LiveData<List<Word>>)allWordsLive;
    }
    void insertWord(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    void deleteWord(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }
    void updateWord(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }
    void clearWord(Word... words){
        new ClearAsyncTask(wordDao).execute();
    }

/*classes*/
    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void>{
        WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }
    static class ClearAsyncTask extends AsyncTask<Word, Void, Void>{
        WordDao wordDao;

        public ClearAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteAllWords();
            return null;
        }
    }
    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void>{
        WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }
}
