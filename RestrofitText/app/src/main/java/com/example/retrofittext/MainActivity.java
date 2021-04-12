package com.example.retrofittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity ";
    private Car car;
    private BookData mBookData;
    private List<OthersDigestData> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNoteRequest();
    }

    private void getRequest2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        Call<OthersDigestData> digestDataCall = api.getCall2("1");
        Log.d(TAG,"Call back is~~~~" + digestDataCall.toString());

        digestDataCall.enqueue(new Callback<OthersDigestData>() {
            @Override
            public void onResponse(Call<OthersDigestData> call, Response<OthersDigestData> response) {
                Log.d(TAG,"onResponse----------" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"Json--------" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<OthersDigestData> call, Throwable t) {
                Log.d(TAG,"error for note!!!!");
            }
        });
    }

    private void getNoteRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService api = retrofit.create(BookService.class);
        Call<List<OthersDigestData>> digestDataCall = api.getCall2("1");

        digestDataCall.enqueue(new Callback<List<OthersDigestData>>() {
            @Override
            public void onResponse(Call<List<OthersDigestData>> call, Response<List<OthersDigestData>> response) {
                Log.d(TAG,"onResponse----------" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"Json--------" + response.body().toString());
                    mDataList = response.body();
                    if (mDataList != null){
                        Log.d(TAG,"one of the list is  " + mDataList.get(0).toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OthersDigestData>> call, Throwable t) {
                Log.d(TAG,"error for note!!!!");
            }
        });
    }



    public void getRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://124.71.184.107:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        Call<BookData> task = api.getCall();
        //enqueue()方法：异步发送请求并将其响应通知回调，或者在与服务器交谈、创建请求或处理响应时发生错误
        task.enqueue(new Callback<BookData>() {
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                Log.d(TAG,"onResponse:" + response.code());
                if (response.code() == HttpsURLConnection.HTTP_OK){
                    Log.d(TAG,"json:" + response.body().toString());//json:com.example.retrofittext.Car@5551560
                    Gson gson = new Gson();
                    //Type mListType = new TypeToken<ArrayList<Car>>(){}.getType();
                    //fromJson(String  json,
                    //                      Class <T> classOfT):返回字符串中T类型的对象。如果json为null或json为空，则返回null。
                   // mBookData = gson.fromJson(String.valueOf(response.body()),BookData.class);
                   // List<String> mList = mBookData.getTranslation();
                   // Log.d(TAG,"data>>>>>" + mList.get(0) + "<<<<<<<");

                }
            }

            @Override
            public void onFailure(Call<BookData> call, Throwable t) {
                Log.d(TAG,"onFalse:" + t.toString());

            }
        });

    }

    public void getBookRequest(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://127.0.0.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService api = retrofit.create(BookService.class);
        Call<ResponseBody> task = api.getCall();
        //enqueue()方法：异步发送请求并将其响应通知回调，或者在与服务器交谈、创建请求或处理响应时发生错误
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"onResponse>>>>>>>" + response.code());
                if (response.code() == HttpsURLConnection.HTTP_OK){
                    try {
                        Log.d(TAG,"json>>>>>>>>" + response.body().string());//json:com.example.retrofittext.Car@5551560
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*Gson gson = new Gson();
                    //Type mListType = new TypeToken<ArrayList<Car>>(){}.getType();
                    //fromJson(String  json,
                    //                      Class <T> classOfT):返回字符串中T类型的对象。如果json为null或json为空，则返回null。
                    car = gson.fromJson(String.valueOf(response.body()),Car.class);
                    List<String> mList = car.getTranslation();
                    Log.d(TAG,"data>>>>>" + mList.get(0) + "<<<<<<<");*/

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"onFalse:" + t.toString());

            }
        });

    }

}