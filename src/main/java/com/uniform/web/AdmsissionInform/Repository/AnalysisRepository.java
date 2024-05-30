package com.uniform.web.AdmsissionInform.Repository;

import com.uniform.web.AdmsissionInform.entity.analysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AnalysisRepository extends JpaRepository<analysisEntity,Integer> {
    ArrayList<analysisEntity> findAllByFieldsAndDepartment(String fields, String Department);
}
