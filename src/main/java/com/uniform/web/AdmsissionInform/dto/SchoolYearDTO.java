package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.entity.SchoolYearEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchoolYearDTO {
    private int school_year_id;
    private int school_year;
    private int school_term;

    public static SchoolYearDTO toSchoolYearDTO(SchoolYearEntity schoolYearEntity){
        SchoolYearDTO schoolYearDTO = new SchoolYearDTO();
        schoolYearDTO.setSchool_year(schoolYearEntity.getSchoolYear());
        schoolYearDTO.setSchool_year_id(schoolYearEntity.getSchoolYearId());
        schoolYearDTO.setSchool_term(schoolYearEntity.getSchoolTerm());

        return schoolYearDTO;
    }
}

