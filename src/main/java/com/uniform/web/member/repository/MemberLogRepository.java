package com.uniform.web.member.repository;

import com.uniform.web.member.entity.MemberLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLogRepository extends JpaRepository<MemberLogEntity, Long> {
    // 추가적인 쿼리 메서드가 필요한 경우 여기에 작성할 수 있습니다.
}