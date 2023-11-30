package com.uniform.web.member.controller;

import com.uniform.web.member.dto.MemberDTO;
import com.uniform.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")    // name값을 requestparam에 담아온다
    public String save(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);

        // 비밀번호와 비밀번호 확인이 일치하지 않으면 에러 메시지를 모델에 추가하고 회원가입 페이지로 이동
        if (!memberDTO.isPasswordMatch()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;  // 뷰 이름이 필요x
        }

        // 성공
        memberService.save(memberDTO);
        // 여기 200 리퀘스트로 변경하기만 하면 됨
        return "hello";
    }
}
