package com.example.demogit.presenter;

import com.example.demogit.view.BaseView;

//每个Activity对应一个Presenter，都继承自BasePresenter，mvp中的逻辑代码都放在此类中。
// Presenter中持有Activity实现接口的引用。所以Presenter可以调用Activity中的代码。
public abstract class BasePresenter<V extends BaseView> {
    protected V mView;

    public BasePresenter(V mView){
        this.mView = mView;
    }
}
