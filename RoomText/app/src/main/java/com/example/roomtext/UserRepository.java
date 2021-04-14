package com.example.roomtext;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    UserRepository(Application application){
        //db
        AppDataBase dataBase = AppDataBase.getDataBase(application);
        mUserDao = dataBase.userDao();
        mAllUsers = mUserDao.getAll();
    }
    //returns the cached words as LiveData. Room executes all queries on a separate thread.
    // Observed LiveData notifies the observer when the data changes??
    LiveData<List<User>> getAllUsers(){
        return mAllUsers;
    }
//Add a wrapper for the insert() method.?
    public void insert(User user){
        new insertAsyncTask(mUserDao).execute(user);
    }
//???
    private static class insertAsyncTask extends AsyncTask<User,Void,Void>{

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... users) {
            mAsyncTaskDao.insertUser(users[0]);
            return null;
        }
    }
}
