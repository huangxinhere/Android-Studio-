package com.example.roomtext;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;
    private LiveData<List<User>> mAllUsers;

    public UserViewModel(Application application){
        super(application);
        //获得下方的库和库里面的数据
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    LiveData<List<User>> getAllUsers(){
        return mAllUsers;
    }

    public void insert(User user){
        mRepository.insert(user);
    }
}
