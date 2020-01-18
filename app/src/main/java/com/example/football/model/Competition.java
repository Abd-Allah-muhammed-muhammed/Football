package com.example.football.model;

import com.google.gson.annotations.SerializedName;

public class Competition {


    @SerializedName("area")
    private Area mArea;
    @SerializedName("code")
    private Object mCode;
    @SerializedName("currentSeason")
    private CurrentSeason mCurrentSeason;
    @SerializedName("emblemUrl")
    private Object mEmblemUrl;
    @SerializedName("id")
    private Long mId;
    @SerializedName("lastUpdated")
    private String mLastUpdated;
    @SerializedName("name")
    private String mName;
    @SerializedName("numberOfAvailableSeasons")
    private Long mNumberOfAvailableSeasons;
    @SerializedName("plan")
    private String mPlan;

    public Area getArea() {
        return mArea;
    }

    public void setArea(Area area) {
        mArea = area;
    }

    public Object getCode() {
        return mCode;
    }

    public void setCode(Object code) {
        mCode = code;
    }

    public CurrentSeason getCurrentSeason() {
        return mCurrentSeason;
    }

    public void setCurrentSeason(CurrentSeason currentSeason) {
        mCurrentSeason = currentSeason;
    }

    public Object getEmblemUrl() {
        return mEmblemUrl;
    }

    public void setEmblemUrl(Object emblemUrl) {
        mEmblemUrl = emblemUrl;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastUpdated() {
        return mLastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        mLastUpdated = lastUpdated;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getNumberOfAvailableSeasons() {
        return mNumberOfAvailableSeasons;
    }

    public void setNumberOfAvailableSeasons(Long numberOfAvailableSeasons) {
        mNumberOfAvailableSeasons = numberOfAvailableSeasons;
    }

    public String getPlan() {
        return mPlan;
    }

    public void setPlan(String plan) {
        mPlan = plan;
    }

}
