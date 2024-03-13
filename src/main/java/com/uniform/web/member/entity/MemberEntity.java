package com.uniform.web.member.entity;

import com.uniform.web.member.dto.MemberDTO;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "member_table") //database에 해당 이름의 테이블 생성
public class MemberEntity { //table 역할
    //jpa ==> database를 객체처럼 사용 가능

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    private String memberId;
    @Column(name = "user_name", nullable = false, length = 7)
    private String memberName;
    @Column(name = "user_pw", nullable = false, length = 20)
    private String memberPassword;
    @Column(name = "phone_number", nullable = false, length = 11)
    private String memberPhoneNumber;
    @Column(name = "user_join_date", nullable = false)
    private LocalDateTime memberJoinDate;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberPhoneNumber(memberDTO.getMemberPhoneNumber());
        memberEntity.setMemberJoinDate(LocalDateTime.now());
        return memberEntity;
    }

}
