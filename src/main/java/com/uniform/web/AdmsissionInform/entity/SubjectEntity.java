package com.uniform.web.AdmsissionInform.entity;

import com.uniform.web.AdmsissionInform.dto.SubjectDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @Column(name ="subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subject_id;

    @Column(name ="curriculum")
    private String curriculum;

    @Column(name ="subject")
    private String subject;

    public static SubjectEntity toSubjectEntity(SubjectDTO subjectDTO){
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setSubject_id(subjectDTO.getSubject_id());
        subjectEntity.setCurriculum(subjectDTO.getCurriculum());
        subjectEntity.setSubject(subjectDTO.getSubject());
        return subjectEntity;
    }
}

