package com.example.demogit.api;

import com.example.demogit.bean.User;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("repo")
    Observable<User> pull(@Query("lang") String lang,
                               @Query("since") String since);
}
