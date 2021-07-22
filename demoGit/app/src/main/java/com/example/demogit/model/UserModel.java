package com.example.demogit.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.demogit.BuildConfig;
import com.example.demogit.Contract.MainContract2;
import com.example.demogit.api.Api;
import com.example.demogit.bean.User;
import com.example.demogit.retrofitManage.RetrofitManager;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserModel implements BaseModel, MainContract2.IModel {
    private static final String TAG = "UserModel" ;
    private List<User.ItemsBean> items;
    private User users;
    private Api api;

    public UserModel() {
        api = RetrofitManager.getInstance().createApi(Api.class);
    }

    @Override
    public void request(CallBack callBack) {

        api.pull("java","weekly")
                .subscribeOn(Schedulers.io())//上游在子线程
                .observeOn(AndroidSchedulers.mainThread())//下游在主线程
                .subscribe(new Observer<User>() {//建立连接，下游
                    @Override
                    public void onSubscribe(Disposable d) {
                        //在接收状态中
                        Log.e(TAG,"onSubscribe: ");
                    }

                    @Override
                    public void onNext(User value) {
                        //执行任务
                        Log.e(TAG,"onNext: " + value.toString());
                        users = value;
                        items = users.getItems();
                        Log.e(TAG,items.get(0).getRepo());

                        callBack.onSuccess(items);//还不知道具体实现方法
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure("请求失败！");
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete("请求成功！");
                    }
                });
    }

   /* private static Retrofit create(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);//设置请求超时时间
        builder.connectTimeout(9,TimeUnit.SECONDS);
        //??
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return new Retrofit.Builder()
                .baseUrl( "https://trendings.herokuapp.com/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }*/


}
