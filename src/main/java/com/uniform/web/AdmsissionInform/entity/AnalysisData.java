package com.uniform.web.AdmsissionInform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalysisData {
    @JsonProperty("school")
    private String school;
    @JsonProperty("major")
    private String major;
    @JsonProperty("admissionProbability")
    private String prob;
    @JsonProperty("danger")
    private String danger;

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
    @JsonProperty("admissionProbability")
    public String getProb() {
        return prob;
    }
    @JsonProperty("admissionProbability")
    public void setProb(String prob) {
        this.prob = prob;
    }
    @JsonProperty("danger")
    public String getDanger() {
        return danger;
    }
    @JsonProperty("danger")
    public void setDanger(String danger) {
        this.danger = danger;
    }
}
