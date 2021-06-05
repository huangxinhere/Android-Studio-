package com.example.demogit.model;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.demogit.BuildConfig;
import com.example.demogit.api.Api;
import com.example.demogit.bean.User;
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

public class UserImp implements UserModel{
    private static final String TAG = "UserImp" ;
    private List<User.ItemsBean> items;//static??
    private User users;
    private Context context;

    public UserImp(Context context) {
        this.context = context;
    }

    @Override
    public List<User.ItemsBean> request() {
        return null;
    }
/*
    @Override
    public List<User.ItemsBean> request() {

        Retrofit com.example.demogit.retrofit = create();
        Api api = com.example.demogit.retrofit.create(Api.class);

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
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                        Log.e(TAG,"请求失败");
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(context,"请求成功",Toast.LENGTH_SHORT).show();
                        Log.e(TAG,"请求成功");
                    }
                });
        return items;//整个内部类实现后，会返回相应的值吗
    }

    private static Retrofit create(){
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

    }
*/

}
