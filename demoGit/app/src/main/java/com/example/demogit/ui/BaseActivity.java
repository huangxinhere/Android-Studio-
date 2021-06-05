package com.example.demogit.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demogit.presenter.BasePresenter;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
    }

    public abstract T createPresenter();
}
