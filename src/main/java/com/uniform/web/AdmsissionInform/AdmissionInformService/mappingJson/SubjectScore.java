package com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SubjectScore {
    private int schoolYear;
    private int schoolTerm;
    private String subjectName;
    private String curriculum;
    private int credit;
    private double rawScore;
    private double subjectMean;
    private int headCount;
    private int ranking;
    private double sDeviation;
}
