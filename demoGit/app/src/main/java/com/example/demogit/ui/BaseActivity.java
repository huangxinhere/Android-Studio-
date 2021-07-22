package com.example.demogit.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demogit.presenter.BasePresenter;
import com.example.demogit.view.BaseView;

//基类BaseActivity，封装一些通用方法便于其他模块的Activity进行扩展
//在此类中实现了IView接口，所以在之后的Activity中不在需要实现IView接口

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //初始化布局
        if (initLayout() instanceof Integer){
            setContentView((Integer) initLayout());
        } else if (initLayout() instanceof View){
            setContentView((View) initLayout());
        } else {
            throw new IllegalArgumentException("initLayout() 应该返回Int或者View类型对象");
        }

        //绑定presenter
        mPresenter = createPresenter();
        mPresenter.attachView(this);

        //初始化组件
        initView();
    }

    public abstract T createPresenter();
    protected abstract Object initLayout();
    protected abstract void initView();
}
