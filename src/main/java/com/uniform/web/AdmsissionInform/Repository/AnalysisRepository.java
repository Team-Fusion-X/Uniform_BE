package com.uniform.web.AdmsissionInform.Repository; // 올바른 패키지 선언

import com.uniform.web.AdmsissionInform.entity.analysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList; // ArrayList import 추가

@Repository
public interface AnalysisRepository extends JpaRepository<analysisEntity, Integer>, JpaSpecificationExecutor<analysisEntity> {
    ArrayList<analysisEntity> findAllByFieldsAndDepartment(String fields, String department);
}
