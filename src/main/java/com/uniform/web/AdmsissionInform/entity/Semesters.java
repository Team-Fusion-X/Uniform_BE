package com.uniform.web.AdmsissionInform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Semesters {
    @JsonProperty("1semester")
    private List<SubjectScore> FirstSemester;
    @JsonProperty("2semester")
    private List<SubjectScore> SecondSemester;
    @JsonProperty("1semester")
    public List<SubjectScore> getSecondSemester() {
        return SecondSemester;
    }
    @JsonProperty("1semester")
    public void setSecondSemester(List<SubjectScore> secondSemester) {
        SecondSemester = secondSemester;
    }
    @JsonProperty("2semester")
    public List<SubjectScore> getFirstSemester() {
        return FirstSemester;
    }
    @JsonProperty("2semester")
    public void setFirstSemester(List<SubjectScore> firstSemester) {
        FirstSemester = firstSemester;
    }
}
