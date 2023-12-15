package com.uniform.web.AdmsissionInform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class gpaData {
    @JsonProperty("school")
    private String school;
    @JsonProperty("major")
    private String major;
    @JsonProperty("ResearchAvg")
    private double researchAvg;
    @JsonProperty("ResearchPercentile")
    private double precen;
    @JsonProperty("AllSubjectAvg")
    private double allSubjectAvg;
    @JsonProperty("ScienceAvg")
    private double scienceAvg;
    @JsonProperty("school")
    public String getSchool() {
        return school;
    }
    @JsonProperty("school")
    public void setSchool(String school) {
        this.school = school;
    }
    @JsonProperty("major")
    public String getMajor() {
        return major;
    }
    @JsonProperty("major")
    public void setMajor(String major) {
        this.major = major;
    }
    @JsonProperty("ResearchAvg")
    public double getResearchAvg() {
        return researchAvg;
    }
    @JsonProperty("ResearchAvg")
    public void setResearchAvg(double researchAvg) {
        this.researchAvg = researchAvg;
    }
    @JsonProperty("ResearchPercentile")
    public double getPrecen() {
        return precen;
    }
    @JsonProperty("ResearchPercentile")
    public void setPrecen(double precen) {
        this.precen = precen;
    }
    @JsonProperty("AllSubjectAvg")
    public double getAllSubjectAvg() {
        return allSubjectAvg;
    }
    @JsonProperty("AllSubjectAvg")
    public void setAllSubjectAvg(double allSubjectAvg) {
        this.allSubjectAvg = allSubjectAvg;
    }
    @JsonProperty("ScienceAvg")
    public double getScienceAvg() {
        return scienceAvg;
    }
    @JsonProperty("ScienceAvg")
    public void setScienceAvg(double scienceAvg) {
        this.scienceAvg = scienceAvg;
    }
}
