package com.arifpurnama.courseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.arifpurnama.courseapp.adapter.CourseForYouAdapter;
import com.arifpurnama.courseapp.adapter.PopularCourseAdapter;
import com.arifpurnama.courseapp.model.CourseData;
import com.arifpurnama.courseapp.model.CourseForYou;
import com.arifpurnama.courseapp.model.PopularCourse;
import com.arifpurnama.courseapp.retrofit.ApiInterface;
import com.arifpurnama.courseapp.retrofit.RetrotifClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView popularRecycler, courseForYouRecycler;
    PopularCourseAdapter popularCourseAdapter;
    CourseForYouAdapter courseForYouAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrotifClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<CourseData>> call = apiInterface.getAllCourses();
        call.enqueue(new Callback<List<CourseData>>() {
            @Override
            public void onResponse(Call<List<CourseData>> call, Response<List<CourseData>> response) {
                List<CourseData> courseDataList = response.body();

                setPopularRecycler(courseDataList.get(0).getPopularCourses());
                setCourseForYouRecycler(courseDataList.get(0).getCourseForYou());

            }

            @Override
            public void onFailure(Call<List<CourseData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Respon From Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPopularRecycler(List<PopularCourse> popularCourseList){
        popularRecycler = findViewById(R.id.popular_recyler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularCourseAdapter = new PopularCourseAdapter(this, popularCourseList);
        popularRecycler.setAdapter(popularCourseAdapter);
    }

    private void setCourseForYouRecycler(List<CourseForYou> courseForYouList){
        courseForYouRecycler = findViewById(R.id.course_for_you);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        courseForYouRecycler.setLayoutManager(layoutManager);
        courseForYouAdapter = new CourseForYouAdapter(this, courseForYouList);
        courseForYouRecycler.setAdapter(courseForYouAdapter);
    }

}