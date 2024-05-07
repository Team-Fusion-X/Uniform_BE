package com.uniform.web.AdmsissionInform.contorller;

import com.uniform.web.AdmsissionInform.AdmissionInformService.SubjectService;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.GetScore;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.GetSubjects;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.WrappingGetScore;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.postScore;
import com.uniform.web.AdmsissionInform.Repository.ScoreRepository;
import com.uniform.web.AdmsissionInform.entity.*;
import com.uniform.web.AdmsissionInform.AdmissionInformService.AdmissionInformService;
import com.uniform.web.member.entity.MemberEntity;
import com.uniform.web.member.repository.MemberRepository;
import com.uniform.web.member.sessionKey.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdmissionInformController {
    private final SubjectService subjectService;
    private final ScoreRepository scoreRepository;
    private final MemberRepository memberRepository;

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
    @PostMapping("/score")
    public ResponseEntity<?> saveScore(@RequestBody postScore PostScore,HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String memberId = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (memberId == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        MemberEntity memberEntity = subjectService.findMemberEntity(memberId);
        ScoreEntity scoreEntity = new ScoreEntity();
        SubjectsEntity subjectsEntity = subjectService.findSubjectEntity(PostScore.getSubjectName(), PostScore.getCurriculum());
        SchoolYearEntity schoolYearEntity = subjectService.findSchoolEntity(PostScore.getSchoolYear(),PostScore.getSchoolTerm());
        scoreEntity.setRaw_score(PostScore.getRawScore());
        scoreEntity.setUserId(memberEntity);
        scoreEntity.setCredit(PostScore.getCredit());
        scoreEntity.setRanking(PostScore.getRanking());
        scoreEntity.setSubject_id(subjectsEntity);
        scoreEntity.setHeadcount(PostScore.getHeadCount());
        scoreEntity.setSubject_average(PostScore.getSubjectMean());
        scoreEntity.setStandard_deviation(PostScore.getSDeviation());
        scoreEntity.setSchool_year_id(schoolYearEntity);
        if (memberEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"no member\"");
        }
        if (subjectsEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"wrong subject\"");
        }
        if (schoolYearEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"wrong school year\"");
        }
        if (subjectService.saveScore(scoreEntity)){
            return ResponseEntity.status(HttpStatus.OK).body("저장 완료");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("오류");
        }
    }
    @GetMapping("/analysis/test")
    public AnalysisData test(@RequestBody SchoolInfo schoolInfo){
        AnalysisData analysisData = new AnalysisData();
        analysisData.setDanger("6");
        analysisData.setProb("11");
        analysisData.setMajor("컴퓨터공학과");
        analysisData.setSchool("전남대학교");
        return analysisData;
    }
    @GetMapping("/score")
    public ResponseEntity<?> getScore(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String member = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        ArrayList<ScoreEntity> scores = scoreRepository.findScoreEntitiesByUserId(memberRepository.findAllByMemberId(member));
        if (scores == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"empty scores\"");
        }
        ArrayList<GetScore> getScores = new ArrayList<>();
        for(ScoreEntity scoreEntity : scores){
            GetScore tmp = new GetScore();
            tmp.setSchoolYear(scoreEntity.getSchool_year_id().getSchoolYear());
            tmp.setSchoolTerm(scoreEntity.getSchool_year_id().getSchoolTerm());
            tmp.setSubjectName(scoreEntity.getSubject_id().getSubject());
            tmp.setCurriculum(scoreEntity.getSubject_id().getCurriculum());
            tmp.setRawScore(scoreEntity.getRaw_score());
            tmp.setSubjectMean(scoreEntity.getSubject_average());
            tmp.setSDeviation(scoreEntity.getStandard_deviation());
            tmp.setHeadCount(scoreEntity.getHeadcount());
            tmp.setRanking(scoreEntity.getRanking());
            tmp.setCredit(scoreEntity.getCredit());
            getScores.add(tmp);
        }
        WrappingGetScore wrappingGetScore = new WrappingGetScore();
        wrappingGetScore.setGetScores(getScores);
        return ResponseEntity.status(HttpStatus.OK).body(wrappingGetScore);

    }
    @GetMapping("/subject")
    public ResponseEntity<?> getSubject(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        List<SubjectsEntity> subjectsEntities = subjectService.getAllSubjectsEntities();
        if (subjectsEntities == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"empty subject\"");
        }
        GetSubjects getSubjects = new GetSubjects();
        getSubjects.set국어(getSubjects.getSubjectList(subjectsEntities,"국어"));
        getSubjects.set수학(getSubjects.getSubjectList(subjectsEntities,"수학"));
        getSubjects.set영어(getSubjects.getSubjectList(subjectsEntities,"영어"));
        getSubjects.set사회(getSubjects.getSubjectList(subjectsEntities,"사회"));
        getSubjects.set과학(getSubjects.getSubjectList(subjectsEntities,"과학"));
        getSubjects.set체육(getSubjects.getSubjectList(subjectsEntities,"체육"));
        getSubjects.set예술(getSubjects.getSubjectList(subjectsEntities,"예술"));
        getSubjects.set기술가정(getSubjects.getSubjectList(subjectsEntities,"기술가정"));

        return ResponseEntity.status(HttpStatus.OK).body(getSubjects);

    }


}