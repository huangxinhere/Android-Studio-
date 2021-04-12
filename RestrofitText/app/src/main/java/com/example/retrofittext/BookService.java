package com.example.retrofittext;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookService {
    @GET("Library/:book_id")
    Call<ResponseBody> getCall();

    @GET("Library/{books_id}/digest")
    Call<List<OthersDigestData>> getCall2(@Path("books_id") String books_id);

}
