package com.uniform.web.AdmsissionInform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectScore {

    @JsonProperty("교과")
    private String subject;
    @JsonProperty("과목")
    private String course;
    @JsonProperty("단위수")
    private int weight;
    @JsonProperty("원점수")
    private double RawScore;
    @JsonProperty("과목평균")
    private double scoreAvg;
    @JsonProperty("표준편차")
    private double StandardD;
    @JsonProperty("수강자수")
    private int studentNum;
    @JsonProperty("석차등급")
    private int rank;

    @JsonProperty("교과")
    public String getSubject() {
        return subject;
    }
    @JsonProperty("교과")
    public void setSubject(String subject) {
        this.subject = subject;
    }
    @JsonProperty("과목")
    public String getCourse() {
        return course;
    }
    @JsonProperty("과목")
    public void setCourse(String course) {
        this.course = course;
    }
    @JsonProperty("단위수")
    public int getWeight() {
        return weight;
    }
    @JsonProperty("단위수")
    public void setWeight(int weight) {
        this.weight = weight;
    }
    @JsonProperty("과목평균")
    public double getScoreAvg() {
        return scoreAvg;
    }
    @JsonProperty("과목평균")
    public void setScoreAvg(double scoreAvg) {
        this.scoreAvg = scoreAvg;
    }
    @JsonProperty("표준편차")
    public double getStandardD() {
        return StandardD;
    }
    @JsonProperty("표준편차")
    public void setStandardD(double standardD) {
        StandardD = standardD;
    }
    @JsonProperty("수강자수")
    public int getStudentNum() {
        return studentNum;
    }
    @JsonProperty("수강자수")
    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }
    @JsonProperty("석차등급")
    public int getRank() {
        return rank;
    }
    @JsonProperty("석차등급")
    public void setRank(int rank) {
        this.rank = rank;
    }
    @JsonProperty("원점수")
    public double getRawScore() {
        return RawScore;
    }
    @JsonProperty("원점수")
    public void setRawScore(double rawScore) {
        RawScore = rawScore;
    }
}
