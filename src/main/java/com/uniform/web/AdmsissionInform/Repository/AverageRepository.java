package com.uniform.web.AdmsissionInform.Repository;

import com.uniform.web.AdmsissionInform.entity.AverageEntity;
import com.uniform.web.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AverageRepository extends JpaRepository<AverageEntity,Integer> {
    AverageEntity findAllByAverageIdAndUserId(int average_id,MemberEntity user_id);
    List<AverageEntity> findAllByAverageId(MemberEntity user_id);
}
