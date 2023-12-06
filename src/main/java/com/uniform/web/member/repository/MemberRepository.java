package com.uniform.web.member.repository;

import com.uniform.web.member.entity.MemberEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 첫번째 인자 : 어떤 Entity인지, 두번째 인자 : pk 어떤 타입인지
public interface MemberRepository extends JpaRepository<MemberEntity, Long>
{
    public boolean existsByMemberId(String user_id); //existByMemberId 클래스 정의


    MemberEntity findAllByMemberId(String user_id);
}
