
package com.example.football.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CurrentSeason {

    @SerializedName("currentMatchday")
    private Long mCurrentMatchday;
    @SerializedName("endDate")
    private String mEndDate;
    @SerializedName("id")
    private Long mId;
    @SerializedName("startDate")
    private String mStartDate;
    @SerializedName("winner")
    private Object mWinner;

    public Long getCurrentMatchday() {
        return mCurrentMatchday;
    }

    public void setCurrentMatchday(Long currentMatchday) {
        mCurrentMatchday = currentMatchday;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public Object getWinner() {
        return mWinner;
    }

    public void setWinner(Object winner) {
        mWinner = winner;
    }

}
