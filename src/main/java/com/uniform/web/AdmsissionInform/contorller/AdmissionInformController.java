package com.uniform.web.AdmsissionInform.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniform.web.AdmsissionInform.entity.AnalysisData;
import com.uniform.web.AdmsissionInform.entity.SchoolInfo;
import com.uniform.web.AdmsissionInform.entity.gpaData;
import com.uniform.web.AdmsissionInform.AdmissionInformService.AdmissionInformService;
import com.uniform.web.member.sessionKey.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdmissionInformController {

//    @PostMapping("/analysis")
//    public SchoolInfo checkInform(@RequestBody SchoolInfo schoolInfo) {
//        return null;
//    }
    @PostMapping("/analysis")
    public ResponseEntity<?> checkInform(@RequestBody SchoolInfo schoolInfo, HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String member = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        System.out.println(member);
        AdmissionInformService admissionInformService = new AdmissionInformService();
        if (schoolInfo != null &&
                schoolInfo.getScores() != null &&
                schoolInfo.getScores().getFirstYearFirstSemester() != null &&
                schoolInfo.getScores().getFirstYearFirstSemester().size() > 1) {
            gpaData data = admissionInformService.calGPA(schoolInfo);
            // 안전하게 접근
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } else {
            // 처리할 수 없는 경우에 대한 로직 추가
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Error\"");
        }
}
//    @GetMapping("/save")
//    public ResponseEntity<?> saveScore(@RequestBody SchoolInfo schoolInfo){
//
//
//    }
    @GetMapping("/analysis/test")
    public AnalysisData test(@RequestBody SchoolInfo schoolInfo){
        AnalysisData analysisData = new AnalysisData();
        analysisData.setDanger("6");
        analysisData.setProb("11");
        analysisData.setMajor("컴퓨터공학과");
        analysisData.setSchool("전남대학교");
        return analysisData;
    }

}