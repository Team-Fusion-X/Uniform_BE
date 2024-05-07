package com.uniform.web.AdmsissionInform.Repository;

import com.uniform.web.AdmsissionInform.entity.SchoolYearEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolYearRepository extends JpaRepository<SchoolYearEntity,Integer> {
    SchoolYearEntity findAllBySchoolYearAndSchoolTerm(int schoolYear,int schoolTerm);

}
