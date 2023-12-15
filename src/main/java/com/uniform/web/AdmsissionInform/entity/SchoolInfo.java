package com.uniform.web.AdmsissionInform.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

public class SchoolInfo {
    @JsonProperty("preferredSchool")
    private String school;

    @JsonProperty("preferredMajor")
    private String Major;

    @JsonProperty("grade")
    private Scores scores;

    @JsonProperty("preferredSchool")
    public String getSchool() {
        return school;
    }

    @JsonProperty("preferredSchool")
    public void setSchool(String school) {
        this.school = school;
    }

    @JsonProperty("preferredMajor")
    public String getMajor() {
        return Major;
    }

    @JsonProperty("preferredMajor")
    public void setMajor(String major) {
        Major = major;
    }

    @JsonProperty("grade")
    public Scores getScores() {
        return scores;
    }

    @JsonProperty("grade")
    public void setScores(Scores scores) {
        this.scores = scores;
    }
    public static class Scores {
        @JsonProperty("1years1semester")
        List<SubjectScore> FirstYearFirstSemester;
        @JsonProperty("1years2semester")
        List<SubjectScore> FirstYearSecondSemester;
        @JsonProperty("2years1semester")
        List<SubjectScore> SecondYearFirstSemester;
        @JsonProperty("2years2semester")
        List<SubjectScore> SecondYearSecondSemester;
        @JsonProperty("3years1semester")
        List<SubjectScore> ThirdYearFirstSemester;
        @JsonProperty("3years2semester")
        List<SubjectScore> ThirdYearSecondSemester;
        @JsonProperty("1years1semester")
        public List<SubjectScore> getFirstYearFirstSemester() {
            return FirstYearFirstSemester;
        }
        @JsonProperty("1year1semester")
        public void setFirstYearFirstSemester(List<SubjectScore> firstYearFirstSemester) {
            FirstYearFirstSemester = firstYearFirstSemester;
        }
        @JsonProperty("1year2semester")
        public List<SubjectScore> getFirstYearSecondSemester() {
            return FirstYearSecondSemester;
        }
        @JsonProperty("1year2semester")
        public void setFirstYearSecondSemester(List<SubjectScore> firstYearSecondSemester) {
            FirstYearSecondSemester = firstYearSecondSemester;
        }
        @JsonProperty("2year1semester")
        public List<SubjectScore> getSecondYearFirstSemester() {
            return SecondYearFirstSemester;
        }
        @JsonProperty("2year1semester")
        public void setSecondYearFirstSemester(List<SubjectScore> secondYearFirstSemester) {
            SecondYearFirstSemester = secondYearFirstSemester;
        }
        @JsonProperty("2year2semester")
        public List<SubjectScore> getSecondYearSecondSemester() {
            return SecondYearSecondSemester;
        }
        @JsonProperty("2year2semester")
        public void setSecondYearSecondSemester(List<SubjectScore> secondYearSecondSemester) {
            SecondYearSecondSemester = secondYearSecondSemester;
        }
        @JsonProperty("3year1semester")
        public List<SubjectScore> getThirdYearFirstSemester() {
            return ThirdYearFirstSemester;
        }
        @JsonProperty("3year1semester")
        public void setThirdYearFirstSemester(List<SubjectScore> thirdYearFirstSemester) {
            ThirdYearFirstSemester = thirdYearFirstSemester;
        }
        @JsonProperty("3year2semester")
        public List<SubjectScore> getThirdYearSecondSemester() {
            return ThirdYearSecondSemester;
        }
        @JsonProperty("3year2semester")
        public void setThirdYearSecondSemester(List<SubjectScore> thirdYearSecondSemester) {
            ThirdYearSecondSemester = thirdYearSecondSemester;
        }
    }
    public static class SubjectScore {

        @JsonProperty("curriculum")
        private String curriculum;
        @JsonProperty("subject")
        private String subject;
        @JsonProperty("credits")
        private int credits;
        @JsonProperty("rawScore")
        private double rawScore;
        @JsonProperty("subjectAverage")
        private double subjectAverage;
        @JsonProperty("standardDeviation")
        private double StandardD;
        @JsonProperty("stuentsNumber")
        private int stuentsNumber;
        @JsonProperty("rank")
        private int rank;

        @JsonProperty("curriculum")
        public String getCurriculum() {
            return curriculum;
        }
        @JsonProperty("curriculum")
        public void setCurriculum(String curriculum) {
            this.curriculum = curriculum;
        }
        @JsonProperty("subject")
        public String getSubject() {
            return subject;
        }
        @JsonProperty("subject")
        public void setSubject(String subject) {
            this.subject = subject;
        }
        @JsonProperty("credits")
        public int getCredits() {
            return credits;
        }
        @JsonProperty("credits")
        public void setCredits(int credits) {
            this.credits = credits;
        }
        @JsonProperty("subjectAverage")
        public double getSubjectAverage() {
            return subjectAverage;
        }
        @JsonProperty("subjectAverage")
        public void setSubjectAverage(double subjectAverage) {
            this.subjectAverage = subjectAverage;
        }
        @JsonProperty("standardDeviation")
        public double getStandardD() {
            return StandardD;
        }
        @JsonProperty("standardDeviation")
        public void setStandardD(double standardD) {
            StandardD = standardD;
        }
        @JsonProperty("stuentsNumber")
        public int getStuentsNumber() {
            return stuentsNumber;
        }
        @JsonProperty("stuentsNumber")
        public void setStuentsNumber(int stuentsNumber) {
            this.stuentsNumber = stuentsNumber;
        }
        @JsonProperty("rank")
        public int getRank() {
            return rank;
        }
        @JsonProperty("rank")
        public void setRank(int rank) {
            this.rank = rank;
        }
        @JsonProperty("rawScore")
        public double getRawScore() {
            return rawScore;
        }
        @JsonProperty("rawScore")
        public void setRawScore(double rawScore) {
            this.rawScore = rawScore;
        }
    }

}
