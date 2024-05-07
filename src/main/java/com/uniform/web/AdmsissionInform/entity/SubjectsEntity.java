package com.uniform.web.AdmsissionInform.entity;

import com.uniform.web.AdmsissionInform.dto.SubjectDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
@Setter
@Getter
@Table(name = "subjects")
public class SubjectsEntity {
    @Id
    @Column(name ="subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;

    @Column(name ="curriculum")
    @NotNull
    private String curriculum;

    @Column(name ="subject")
    @NotNull
    private String subject;

    public static SubjectsEntity toSubjectEntity(SubjectDTO subjectDTO){
        SubjectsEntity subjectsEntity = new SubjectsEntity();
        subjectsEntity.setSubjectId(subjectDTO.getSubject_id());
        subjectsEntity.setCurriculum(subjectDTO.getCurriculum());
        subjectsEntity.setSubject(subjectDTO.getSubject());
        return subjectsEntity;
    }
}

