package com.arifpurnama.courseapp.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifpurnama.courseapp.CourseDetails;
import com.arifpurnama.courseapp.R;
import com.arifpurnama.courseapp.model.PopularCourse;
import com.bumptech.glide.Glide;

import java.util.List;

public class PopularCourseAdapter extends RecyclerView.Adapter<PopularCourseAdapter.PopularViewHolder> {

    Context context;
    List<PopularCourse> popularCourseList;

    public PopularCourseAdapter(Context context, List<PopularCourse> popularCourseList) {
        this.context = context;
        this.popularCourseList = popularCourseList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_row_item, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PopularViewHolder holder, int position) {
        holder.courseName.setText(popularCourseList.get(position).getName());
        holder.totalLessons.setText(popularCourseList.get(position).getTotalLesson());
        Glide.with(context).load(popularCourseList.get(position).getImageUrl())
                .into(holder.courseImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CourseDetails.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(holder.courseImage, "image");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity)context, pairs);

                context.startActivity(i, activityOptions.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularCourseList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView courseName, totalLessons;
        ImageView courseImage;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.lesson_name);
            totalLessons = itemView.findViewById(R.id.total_lesson);
            courseImage = itemView.findViewById(R.id.course_image);
        }
    }
}
