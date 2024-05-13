package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.SubjectScore;
import com.uniform.web.AdmsissionInform.entity.SchoolInfo;
import com.uniform.web.AdmsissionInform.entity.SchoolYearEntity;
import com.uniform.web.AdmsissionInform.entity.ScoreEntity;
import com.uniform.web.AdmsissionInform.entity.SubjectsEntity;
import com.uniform.web.member.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.uniform.web.AdmsissionInform.AdmissionInformService.AdmissionInformService.cumulativeProbability;

@Getter
@Setter
public class ScoreDTO {
    private int scoreId;
    private MemberEntity userId;
    private SubjectsEntity subjectId;
    private int credit;
    private double rawScore;
    private double subjectAverage;
    private double standardDeviation;
    private int headcount;
    private int ranking;

    //ScoreEntity를 DTO로 변환시키는 메소드
    public static ScoreDTO toScoreDTO(ScoreEntity scoreEntity){
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setSubjectId(scoreEntity.getSubject_id());
        scoreDTO.setScoreId(scoreEntity.getScore_id());
        scoreDTO.setUserId(scoreEntity.getUserId());
        scoreDTO.setCredit(scoreEntity.getCredit());
        scoreDTO.setRawScore(scoreEntity.getRaw_score());
        scoreDTO.setSubjectAverage(scoreEntity.getSubject_average());
        scoreDTO.setStandardDeviation(scoreEntity.getStandard_deviation());
        scoreDTO.setHeadcount(scoreEntity.getHeadcount());
        scoreDTO.setRanking(scoreEntity.getRanking());
        return scoreDTO;
    }

}
