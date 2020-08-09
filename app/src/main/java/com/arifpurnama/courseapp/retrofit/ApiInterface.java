package com.arifpurnama.courseapp.retrofit;

import com.arifpurnama.courseapp.model.CourseData;
import com.arifpurnama.courseapp.model.CourseDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("coursedata.json")
    Call<List<CourseData>> getAllCourses();

    @GET("video_lession.json")
    Call<List<CourseDetail>> getAllLesson();
}
