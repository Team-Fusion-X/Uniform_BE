package com.uniform.web.AdmsissionInform.Repository;

import com.uniform.web.AdmsissionInform.entity.SubjectsEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectsEntity,Integer> {
    SubjectsEntity findAllBySubjectAndCurriculum(String subject, String curriculum);

}
