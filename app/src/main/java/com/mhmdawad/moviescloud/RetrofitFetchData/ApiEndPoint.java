package com.mhmdawad.moviescloud.RetrofitFetchData;

import com.mhmdawad.moviescloud.RetrofitFetchData.JSONData.popular.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("popular")
    Call<MoviesData> getPopular(@Query("api_key") String api_key , @Query("page") int page);

    @GET("now_playing")
    Call<MoviesData> getNowPlaying(@Query("api_key") String api_key , @Query("page") int page);

    @GET("top_rated")
    Call<MoviesData> getTopRated(@Query("api_key") String api_key , @Query("page") int page);

    @GET("upcoming")
    Call<MoviesData> getUpcoming(@Query("api_key") String api_key , @Query("page") int page);
}
