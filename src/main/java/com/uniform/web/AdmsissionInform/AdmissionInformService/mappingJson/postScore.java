package com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class postScore {
    private int schoolYear;
    private int schoolTerm;
    private String subjectName;
    private String curriculum;
    private int credit;
    private double rawScore;
    private double subjectMean;
    private double sDeviation;
    private int headCount;
    private int ranking;
}
