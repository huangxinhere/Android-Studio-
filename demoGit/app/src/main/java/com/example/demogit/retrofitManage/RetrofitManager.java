package com.example.demogit.retrofitManage;

import com.example.demogit.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private Retrofit mRetrofit;
    //单例？
    private static class InstanceHelper{
        static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance(){
        return InstanceHelper.instance;
    }

    private RetrofitManager(){
        mRetrofit = create();
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava2CallAdapterFactory.create()
                .build();
    }

    public <T> T createApi(final Class<T> service){
        return mRetrofit.create(service);
    }

}
