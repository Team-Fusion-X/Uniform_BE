package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.entity.SubjectsEntity;
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

    public static SubjectDTO toSubjectDTO(SubjectsEntity subjectsEntity){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubject_id(subjectsEntity.getSubjectId());
        subjectDTO.setCurriculum(subjectsEntity.getCurriculum());
        subjectDTO.setSubject(subjectsEntity.getSubject());
        return subjectDTO;
    }
}
