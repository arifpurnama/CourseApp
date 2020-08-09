
package com.arifpurnama.courseapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularCourse {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("totalLesson")
    @Expose
    private String totalLesson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(String totalLesson) {
        this.totalLesson = totalLesson;
    }

}
