package com.example.roomtext.Room;

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
    public void deleteAll(){
        new deleteAllUsersAsyncTask(mUserDao).execute();
    }
    public void deleteUser(User user){
        new deleteUserAsyncTask(mUserDao).execute(user);
    }

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

    private static class deleteAllUsersAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao mUserDao;

        deleteAllUsersAsyncTask(UserDao userDao){
            mUserDao = userDao;
        }

        protected Void doInBackground(Void... voids){
            mUserDao.deleteAll();
            return null;
        }
    }

    private static class deleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao mAsyncTaskDao;

        deleteUserAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
