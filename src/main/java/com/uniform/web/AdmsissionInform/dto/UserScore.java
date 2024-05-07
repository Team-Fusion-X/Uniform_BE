package com.uniform.web.AdmsissionInform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserScore {
    private int schoolYear;
    private int schoolTerm;
    private int subjectName;
    private String curriculum;
    private int credit;
    private double rawScore;
    private double subjectMean;
    private double sDeviation;
    private int headCount;
    private int ranking;
}
