package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.entity.ScoreEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDTO {
    private int scoreId;
    private String userId;
    private int schoolYearId;
    private int subjectId;
    private int credit;
    private float rawScore;
    private float subjectAverage;
    private float standardDeviation;
    private int headcount;
    private int ranking;

    //ScoreEntity를 DTO로 변환시키는 메소드
//    public static ScoreDTO toScoreDTO(ScoreEntity scoreEntity){
//        ScoreDTO scoreDTO = new ScoreDTO();
//        scoreDTO.setScoreId(scoreEntity.getScore_id());
//        scoreDTO.setUserId(scoreEntity.getUser_id().getMemberId());
//        scoreDTO.setSchoolYearId(scoreEntity.getSchool_year_id());
//        scoreDTO.setCredit(scoreEntity.getCredit());
//        scoreDTO.setRawScore(scoreEntity.getRaw_score());
//        scoreDTO.setSubjectAverage(scoreEntity.getSubject_average());
//        scoreDTO.setStandardDeviation(scoreEntity.getStandard_deviation());
//        scoreDTO.setRawScore(scoreEntity.getRaw_score());
//        scoreDTO.setRanking(scoreEntity.getRanking());
//        scoreDTO.setHeadcount(scoreEntity.getHeadcount());
//
//        return scoreDTO;
//    }

}
