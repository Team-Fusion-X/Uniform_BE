package com.uniform.web.AdmsissionInform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scores {
    @JsonProperty("1year")
    private Semesters FirstYear;
    @JsonProperty("2year")
    private Semesters SecondYear;
    @JsonProperty("3year")
    private Semesters ThirdYear;
    @JsonProperty("1year")
    public Semesters getFirstYear() {
        return FirstYear;
    }
    @JsonProperty("1year")
    public void setFirstYear(Semesters firstYear) {
        FirstYear = firstYear;
    }
    @JsonProperty("2year")
    public Semesters getSecondYear() {
        return SecondYear;
    }
    @JsonProperty("2year")
    public void setSecondYear(Semesters secondYear) {
        SecondYear = secondYear;
    }
    @JsonProperty("3year")
    public Semesters getThirdYear() {
        return ThirdYear;
    }
    @JsonProperty("3year")
    public void setThirdYear(Semesters thirdYear) {
        ThirdYear = thirdYear;
    }
}
