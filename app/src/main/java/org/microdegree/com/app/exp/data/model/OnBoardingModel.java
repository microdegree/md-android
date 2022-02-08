package org.microdegree.com.app.exp.data.model;

import com.google.gson.annotations.SerializedName;

public class OnBoardingModel {
    @SerializedName("id")
    public int id;

    @SerializedName("image_url")
    public int image_url;

    @SerializedName("title")
    public String title;

    @SerializedName("desc")
    public String description;


    public void setId(int id) {
        this.id = id;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getImage_url() {
        return image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
