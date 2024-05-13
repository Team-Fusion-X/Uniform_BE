package com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class postScore {

    @JsonProperty("credit")
    private int credit;
    @JsonProperty("curriculum")
    private String curriculum;
    @JsonProperty("headCount")
    private int headCount;
    @JsonProperty("ranking")
    private int ranking;
    @JsonProperty("rawScore")
    private double rawScore;
    @JsonProperty("sDeviation")
    private double sDeviation;
    @JsonProperty("schoolTerm")
    private int schoolTerm;
    @JsonProperty("schoolYear")
    private int schoolYear;
    @JsonProperty("subjectMean")
    private double subjectMean;
    @JsonProperty("subjectName")
    private String subjectName;

}
