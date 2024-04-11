package com.uniform.web.member.dto;

import com.uniform.web.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO { //회원 정보를 필드로 정의
    private String memberName; // 이름
    private String memberId; //  primary
    private String memberPassword; // 비밀번호

    //lombok 어노테이션으로 getter,setter,생성자,toString 메서드 생략 가능

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        return memberDTO;
    }

}