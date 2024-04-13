package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.entity.SubjectEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDTO {
    private int subject_id;
    private String curriculum;
    private String subject;

    public static SubjectDTO toSubjectDTO(SubjectEntity subjectEntity){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubject_id(subjectEntity.getSubject_id());
        subjectDTO.setCurriculum(subjectEntity.getCurriculum());
        subjectDTO.setSubject(subjectEntity.getSubject());
        return subjectDTO;
    }
}
