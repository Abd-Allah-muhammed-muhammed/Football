package com.example.football.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompetitionsResponse {



    @SerializedName("competitions")
    private List<Competition> mCompetitions;
    @SerializedName("count")
    private Long mCount;
    @SerializedName("filters")
    private Filters mFilters;

    public List<Competition> getCompetitions() {
        return mCompetitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        mCompetitions = competitions;
    }

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Filters getFilters() {
        return mFilters;
    }

    public void setFilters(Filters filters) {
        mFilters = filters;
    }
}
