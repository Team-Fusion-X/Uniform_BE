package com.uniform.web.member.controller;

import com.uniform.web.member.dto.LoginDTO;
import com.uniform.web.member.dto.MemberDTO;
import com.uniform.web.member.service.MemberService;
import com.uniform.web.member.sessionKey.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/members") // 엔드포인트를 명시적으로 지정
@RequiredArgsConstructor
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    @PostMapping("/check")
    public ResponseEntity<?> checkMember(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {
        String memberId = memberDTO.getMemberId();
        MemberDTO userInfo = memberService.FindByMemberId(memberId);

        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {
        if (memberService.isExistId(memberDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"data\": \"이미 존재하는 아이디입니다.\"}");
        }

        try {
            memberService.save(memberDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            // 저장 실패 시에도 실패 응답을 반환합니다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"data\": \"회원가입에 실패하였습니다.\"}");
        }
    }
    //로그인 컨트롤러 함수
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request){
        String memberLogin = memberService.memberLogin(loginDTO);
        if (memberLogin == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"data\" : \"fail\"");
        }
        //로그인 성공
        HttpSession session = request.getSession();
        //세션에 로그인 정보 저장
        session.setAttribute(SessionConst.LOGIN_MEMBER,memberLogin);
        return ResponseEntity.status(HttpStatus.OK).body("{\n" +
                "\"data\" : \"success\",\n" +
                "\"sessionId:\""+session.getId()+"\"\n" +
                "}");
    }
}
