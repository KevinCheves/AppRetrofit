package com.appmovil.appretrofit.Interface;

import com.appmovil.appretrofit.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface jsonplaceholder {
    @GET("comments")
    Call<List<Posts>> getPosts(@Query("postId") int postid);

}
