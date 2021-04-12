package com.example.retrofittext;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    //@GET注解：采用Get方法发送网络请求
   // @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    //Call<ResponseBody> getCall();
    //getCall（） = 接受网络请求数据的方法
    //其中返回类型为Call<*>,*是接收数据的类
    @GET("library/")
    Call<BookData> getCall();

    @GET("Library/{:books_id}/digest")
    Call<OthersDigestData> getCall2(@Path(":books_id") String num);
}
