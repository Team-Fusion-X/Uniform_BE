package com.uniform.web.AdmsissionInform.contorller;

import com.uniform.web.AdmsissionInform.AdmissionInformService.CalSubjectMeanService;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.WrappingSubjectScore;
import com.uniform.web.AdmsissionInform.Repository.ScoreRepository;
import com.uniform.web.member.sessionKey.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalSubjectmeanController {
    private final ScoreRepository scoreRepository;
    private final CalSubjectMeanService calSubjectMeanService;
    @PostMapping("/average")
    public ResponseEntity<?> calMean(@RequestBody WrappingSubjectScore wrappingSubjectScore, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String member = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        if (calSubjectMeanService.calAllSubject(wrappingSubjectScore) == -1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Error\"");
        }
        return ResponseEntity.status(HttpStatus.OK).body("\"data\" : \"Save\"");

    }
}
