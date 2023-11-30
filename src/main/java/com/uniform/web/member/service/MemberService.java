package com.uniform.web.member.service;

import com.uniform.web.member.dto.MemberDTO;
import com.uniform.web.member.entity.MemberEntity;
import com.uniform.web.member.entity.MemberLogEntity;
import com.uniform.web.member.repository.MemberLogRepository;
import com.uniform.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class MemberService {
    private final MemberRepository memberRepository; // 먼저 jpa, mysql dependency 추가
    private final MemberLogRepository memberLogRepository;

    public void save(MemberDTO memberDTO) {
        // repsitory의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
        memberRepository.save(memberEntity);

        // MemberLogEntity 생성
        MemberLogEntity memberLogEntity = new MemberLogEntity();
        memberLogEntity.setUserId(memberEntity.getMemberId());
        memberLogEntity.setLastLoginDate(LocalDateTime.now());
        memberLogEntity.setManager(false);
        memberLogEntity.setSubscribe(false);
        memberLogEntity.setCurrentlyActive(false);

        System.out.println("memberLogEntity" + memberLogEntity);
        // MemberLogEntity 저장
        memberLogRepository.save(memberLogEntity);

    }
    public boolean isExistId(MemberDTO memberDTO){
        return memberRepository.existsByMemberId(memberDTO.getMemberId()); //MemberRepository에 정의 한 함수이용
    }
}