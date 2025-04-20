package model;

import com.google.gson.annotations.SerializedName;


public class Title implements Comparable<Title> {

    @SerializedName("Title")
    private String name;

    @SerializedName("Year")
    private int releaseYear;
    private boolean includedInThePlan;
    private double sumOfEvaluations;
    private int totalReviews;
    private int durationInMinutes;

    public Title(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isIncludedInThePlan() {
        return includedInThePlan;
    }

    public void setIncludedInThePlan(boolean includedInThePlan) {
        this.includedInThePlan = includedInThePlan;
    }

    public double getSumOfEvaluations() {
        return sumOfEvaluations;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void evaluate(double rating) {
        sumOfEvaluations += rating;
        totalReviews++;
    }

    public double getAverageRating() {
        return sumOfEvaluations / totalReviews;
    }

    @Override
    public int compareTo(Title o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", releaseYear=" + releaseYear;
    }
}
