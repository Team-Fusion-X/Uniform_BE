package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.entity.AverageEntity;
import com.uniform.web.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AverageDTO {
    private int averageId;
    private String userId;
    private double kemrPercentile;
    private double kemrDegree;
    private double kemsDegree;
    private double kemsoDegree;
    private double allSubjectDegree;


    public static AverageDTO toAverageDTO(AverageEntity averageEntity){
        AverageDTO averageDTO = new AverageDTO();
        averageDTO.setAverageId(averageEntity.getAverageId());
        averageDTO.setUserId(averageEntity.getUserId().getMemberId());
        averageDTO.setAllSubjectDegree(averageEntity.getAllSubjectDegree());
        averageDTO.setKemrDegree(averageEntity.getKemrDegree());
        averageDTO.setKemrPercentile(averageEntity.getKemrPercentile());
        averageDTO.setKemsoDegree(averageEntity.getKemsoDegree());
        averageDTO.setKemsDegree(averageEntity.getKemsDegree());

        return averageDTO;
    }
}
