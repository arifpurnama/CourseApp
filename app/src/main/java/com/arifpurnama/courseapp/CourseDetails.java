package com.arifpurnama.courseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.arifpurnama.courseapp.adapter.LessonAdapter;
import com.arifpurnama.courseapp.model.CourseDetail;
import com.arifpurnama.courseapp.model.VideoLession;
import com.arifpurnama.courseapp.retrofit.ApiInterface;
import com.arifpurnama.courseapp.retrofit.RetrotifClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetails extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView lessonRecyler;
    LessonAdapter lessonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        apiInterface = RetrotifClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<CourseDetail>> call = apiInterface.getAllLesson();

        call.enqueue(new Callback<List<CourseDetail>>() {
            @Override
            public void onResponse(Call<List<CourseDetail>> call, Response<List<CourseDetail>> response) {
                List<CourseDetail> courseDetailList = response.body();
                setLessonList(courseDetailList.get(0).getVideoLession());
            }

            @Override
            public void onFailure(Call<List<CourseDetail>> call, Throwable t) {
                Toast.makeText(CourseDetails.this, "No Response from Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLessonList(List<VideoLession> videoLessionList){
        lessonRecyler = findViewById(R.id.lesson_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lessonRecyler.setLayoutManager(layoutManager);
        lessonAdapter = new LessonAdapter(this, videoLessionList);
        lessonRecyler.setAdapter(lessonAdapter);
    }
}