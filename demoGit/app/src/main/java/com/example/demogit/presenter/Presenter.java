package com.example.demogit.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.demogit.Contract.MainContract2;
import com.example.demogit.api.Api;
import com.example.demogit.bean.User;
import com.example.demogit.model.UserModel;
import com.example.demogit.retrofitManage.RetrofitManager;
import com.example.demogit.ui.MainActivity;
import com.example.demogit.view.BaseView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter extends BasePresenter<MainActivity, UserModel> implements MainContract2.IPresenter {
    private static final String TAG = "Presenter";

    @Override
    public void load() {

        mModel.request(new MainContract2.IModel.CallBack() {
            @Override
            public void onSuccess(List<User.ItemsBean> itemsBeans) {
                mView.show_list(itemsBeans);
            }

            @Override
            public void onFailure(String data) {
                mView.show_failure(data);
            }

            @Override
            public void onComplete(String data) {
                mView.show_complete(data);
            }
        });
    }

    @Override
    protected UserModel initModel() {
        return new UserModel();
    }
}
