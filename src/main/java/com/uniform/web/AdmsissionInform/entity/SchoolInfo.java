package com.uniform.web.AdmsissionInform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolInfo {
    @JsonProperty("학교")
    private String school;
    @JsonProperty("성적")
    private Scores scores;

    @JsonProperty("학교")
    public String getSchool() {
        return school;
    }
    @JsonProperty("학교")
    public void setSchool(String school) {
        this.school = school;
    }
    @JsonProperty("성적")
    public Scores getScores() {
        return scores;
    }
    @JsonProperty("성적")
    public void setScores(Scores scores) {
        this.scores = scores;
    }
}
