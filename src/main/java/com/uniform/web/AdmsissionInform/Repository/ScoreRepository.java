package com.uniform.web.AdmsissionInform.Repository;

import com.uniform.web.AdmsissionInform.entity.ScoreEntity;
import com.uniform.web.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.ArrayList;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity,Integer> {
    ArrayList<ScoreEntity> findScoreEntitiesByUserId(MemberEntity userId);
}
