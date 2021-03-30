package com.example.movieappschool.domain;

import java.util.List;

public class Movie {
    private int mId;
    private String mTitle;
    private String mDescription;
    private String mLanguage;
    private List<Integer> mGenreIds;
    private String mReleaseDate;
    private String mPosterURL;
    private double mRatingAverage;
    private int mRatingCount;

    public Movie(int id, String title, String description, String language, List<Integer> genreIds, String releaseDate, String posterURL, double ratingAverage, int ratingCount) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mLanguage = language;
        mGenreIds = genreIds;
        mReleaseDate = releaseDate;
        mPosterURL = posterURL;
        mRatingAverage = ratingAverage;
        mRatingCount = ratingCount;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPosterURL() {
        return mPosterURL;
    }

    public double getRatingAverage() {
        return mRatingAverage;
    }

    public int getRatingCount() {
        return mRatingCount;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mLanguage='" + mLanguage + '\'' +
                ", mGenreIds=" + mGenreIds +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mPosterURL='" + mPosterURL + '\'' +
                ", mRatingAverage=" + mRatingAverage +
                ", mRatingCount=" + mRatingCount +
                '}';
    }
}
