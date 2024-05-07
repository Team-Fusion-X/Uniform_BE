package com.uniform.web.AdmsissionInform.dto;


import com.uniform.web.AdmsissionInform.entity.SchoolEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolDTO {
    private int schoolId;
    private int regionId;
    private String schoolName;


    //SchoolEntity를 DTO로 변환하는 메소드
    public static SchoolDTO toSchoolDTO(SchoolEntity schoolEntity){
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setSchoolId(schoolEntity.getSchoolId());
        schoolDTO.setSchoolName(schoolEntity.getSchoolName());
        schoolDTO.setRegionId(schoolEntity.getRegion_id().getRegionId());

        return schoolDTO;
    }
}
