package com.arifpurnama.courseapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrotifClient {

    private static Retrofit retrofit;
    private static String BASE_URL = "https://androidappsforyoutube.s3.ap-south-1.amazonaws.com/course_app2/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
