package com.uniform.web.AdmsissionInform.Repository;

import com.uniform.web.AdmsissionInform.entity.AverageEntity;
import com.uniform.web.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AverageRepository extends JpaRepository<AverageEntity,Integer> {
    AverageEntity findAllByAverageIdAndUserId(int average_id,MemberEntity user_id);

}
