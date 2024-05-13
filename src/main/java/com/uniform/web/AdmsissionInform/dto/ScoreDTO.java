package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.entity.ScoreEntity;
import com.uniform.web.AdmsissionInform.entity.SubjectsEntity;
import com.uniform.web.member.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

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
        scoreDTO.setSubjectId(scoreEntity.getSubjectId());
        scoreDTO.setScoreId(scoreEntity.getScoreId());
        scoreDTO.setUserId(scoreEntity.getUserId());
        scoreDTO.setCredit(scoreEntity.getCredit());
        scoreDTO.setRawScore(scoreEntity.getRawScore());
        scoreDTO.setSubjectAverage(scoreEntity.getSubjectAverage());
        scoreDTO.setStandardDeviation(scoreEntity.getStandardDeviation());
        scoreDTO.setHeadcount(scoreEntity.getHeadcount());
        scoreDTO.setRanking(scoreEntity.getRanking());
        return scoreDTO;
    }

}
