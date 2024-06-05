package com.uniform.web.AdmsissionInform.contorller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniform.web.AdmsissionInform.AdmissionInformService.SubjectSaveService;
import com.uniform.web.AdmsissionInform.AdmissionInformService.SubjectService;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.*;
import com.uniform.web.AdmsissionInform.Repository.AnalysisRepository;
import com.uniform.web.AdmsissionInform.Repository.AnalysisSpecification;
import com.uniform.web.AdmsissionInform.Repository.AverageRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdmissionInformController {
    private final SubjectSaveService subjectSaveService;
    private final ScoreRepository scoreRepository;
    private final MemberRepository memberRepository;
    private final SubjectService subjectService;
    private final AverageRepository averageRepository;
    private final AnalysisRepository analysisRepository;
    @Autowired
    private final RestTemplate restTemplate;
    //    @PostMapping("/analysis")
//    public SchoolInfo checkInform(@RequestBody SchoolInfo schoolInfo) {
//        return null;
//    }
//    @PostMapping("/analysis")
//    public ResponseEntity<?> checkInform(@RequestBody SchoolInfo schoolInfo, HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
//        }
//        String member = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
//        if (member == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
//        }
//        System.out.println(member);
//        AdmissionInformService admissionInformService = new AdmissionInformService();
//        if (schoolInfo != null &&
//                schoolInfo.getScores() != null &&
//                schoolInfo.getScores().getFirstYearFirstSemester() != null &&
//                schoolInfo.getScores().getFirstYearFirstSemester().size() > 1) {
//            gpaData data = admissionInformService.calGPA(schoolInfo);
//            // 안전하게 접근
//            return ResponseEntity.status(HttpStatus.OK).body(data);
//        } else {
//            // 처리할 수 없는 경우에 대한 로직 추가
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Error\"");
//        }
//    }

    @PostMapping("/score")
    public ResponseEntity<?> saveScore(@RequestBody postScore postScore, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String memberId = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (memberId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        MemberEntity memberEntity = subjectSaveService.findMemberEntity(memberId);
        ScoreEntity scoreEntity = new ScoreEntity();
        SubjectsEntity subjectsEntity = subjectSaveService.findSubjectEntity(postScore.getSubjectName(), postScore.getCurriculum());
        SchoolYearEntity schoolYearEntity = subjectSaveService.findSchoolEntity(postScore.getSchoolYear(), postScore.getSchoolTerm());
        scoreEntity.setRawScore(postScore.getRawScore());
        scoreEntity.setUserId(memberEntity);
        scoreEntity.setCredit(postScore.getCredit());
        scoreEntity.setRanking(postScore.getRanking());
        scoreEntity.setSubjectId(subjectsEntity);
        scoreEntity.setHeadcount(postScore.getHeadCount());
        scoreEntity.setSubjectAverage(postScore.getSubjectMean());
        scoreEntity.setStandardDeviation(postScore.getSDeviation());
        scoreEntity.setSchoolYearId(schoolYearEntity);

        if (memberEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"no member\"");
        }
        if (subjectsEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"wrong subject\"");
        }
        if (schoolYearEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"wrong school year\"");
        }
        if (subjectSaveService.saveScore(scoreEntity)) {
            return ResponseEntity.status(HttpStatus.OK).body("저장 완료");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("오류");
        }
    }

    @GetMapping("/analysis/test")
    public AnalysisData test(@RequestBody SchoolInfo schoolInfo) {
        AnalysisData analysisData = new AnalysisData();
        analysisData.setDanger("6");
        analysisData.setProb("11");
        analysisData.setMajor("컴퓨터공학과");
        analysisData.setSchool("전남대학교");
        return analysisData;
    }

    @GetMapping("/score")
    public ResponseEntity<?> getScore(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String member = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        ArrayList<ScoreEntity> scores = scoreRepository.findScoreEntitiesByUserId(memberRepository.findAllByMemberId(member));
        if (scores == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"empty scores\"");
        }
        ArrayList<GetScore> getScores = new ArrayList<>();
        for (ScoreEntity scoreEntity : scores) {
            GetScore tmp = new GetScore();
            tmp.setSchoolYear(scoreEntity.getSchoolYearId().getSchoolYear());
            tmp.setSchoolTerm(scoreEntity.getSchoolYearId().getSchoolTerm());
            tmp.setSubjectName(scoreEntity.getSubjectId().getSubject());
            tmp.setCurriculum(scoreEntity.getSubjectId().getCurriculum());
            tmp.setRawScore(scoreEntity.getRawScore());
            tmp.setSubjectMean(scoreEntity.getSubjectAverage());
            tmp.setSDeviation(scoreEntity.getStandardDeviation());
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
    public ResponseEntity<?> getSubject(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        List<SubjectsEntity> subjectsEntities = subjectService.getAllSubjectsEntities();
        if (subjectsEntities == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"empty subject\"");
        }
        GetSubjects getSubjects = new GetSubjects();
        getSubjects.set국어(getSubjects.getSubjectList(subjectsEntities, "국어"));
        getSubjects.set수학(getSubjects.getSubjectList(subjectsEntities, "수학"));
        getSubjects.set영어(getSubjects.getSubjectList(subjectsEntities, "영어"));
        getSubjects.set사회(getSubjects.getSubjectList(subjectsEntities, "사회"));
        getSubjects.set과학(getSubjects.getSubjectList(subjectsEntities, "과학"));
        getSubjects.set체육(getSubjects.getSubjectList(subjectsEntities, "체육"));
        getSubjects.set예술(getSubjects.getSubjectList(subjectsEntities, "예술"));
        getSubjects.set기술가정(getSubjects.getSubjectList(subjectsEntities, "기술가정"));

        return ResponseEntity.status(HttpStatus.OK).body(getSubjects);

    }

    @PostMapping("/analysis/one")
    public ResponseEntity<?> analysisOne(@RequestBody PostAnalysis postAnalysis, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String member = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String url = "http://114.70.92.44:11030/predict";
        gpaData data = new gpaData();
        List<Object> dataList = new ArrayList<>();
        dataList.add(postAnalysis.getUniversity());
        dataList.add(postAnalysis.getDepartment());
        dataList.add(postAnalysis.getHope());
        AverageEntity averageEntity = new AverageEntity();
        if (member.equals("abc123")) {
            averageEntity = averageRepository.findAllByAverageIdAndUserId(35, memberRepository.findAllByMemberId(member));
        } else if (member.equals("wndyd123")) {
            averageEntity = averageRepository.findAllByAverageIdAndUserId(38, memberRepository.findAllByMemberId(member));
        } else {
            List<AverageEntity> averageEntities = averageRepository.findAllByAverageId(memberRepository.findAllByMemberId(member));
            int averageId = averageEntities.stream()
                    .mapToInt(AverageEntity::getAverageId)
                    .max().getAsInt();
            averageEntity = averageRepository.findAllByAverageIdAndUserId(averageId, memberRepository.findAllByMemberId(member));
        }
        dataList.add((float) averageEntity.getAllSubjectDegree());
        dataList.add((float) averageEntity.getKemsoDegree());
        dataList.add((float) averageEntity.getKemsDegree());
        dataList.add((float) averageEntity.getKemrPercentile());
        dataList.add((float) averageEntity.getKemrDegree());
        data.setData_list(dataList);
//        return ResponseEntity.status(HttpStatus.OK).body(data);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // 여기서 HttpEntity 객체를 생성하여 요청 데이터를 포함합니다.
        HttpEntity<gpaData> httpRequest = new HttpEntity<>(data, httpHeaders);


        // restTemplate의 postForEntity 메소드에 적절한 HttpEntity 객체를 전달합니다.
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpRequest, String.class);


        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode rootNode = mapper.readTree(response.getBody());
                String possibility = rootNode.get("합격 확률").asText(); // JSON 키를 정확히 입력하세요
                possibility = possibility + "%";
                return ResponseEntity.status(HttpStatus.OK).body("{\"possibility\" : \"" + possibility + "\"}");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in JSON parsing");
            }
        }

        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    @PostMapping("/analysis")
    public ResponseEntity<?> analysisMany(@RequestBody GetLine getLine,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String member = (String) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"data\" : \"Invalid Session\"");
        }
        String url = "http://114.70.92.44:11030/predict";
        List<Object> dataList = new ArrayList<>();
        AverageEntity averageEntity = new AverageEntity();
        if (member.equals("abc123")) {
            averageEntity = averageRepository.findAllByAverageIdAndUserId(35, memberRepository.findAllByMemberId(member));
        } else if (member.equals("wndyd123")) {
            averageEntity = averageRepository.findAllByAverageIdAndUserId(38, memberRepository.findAllByMemberId(member));
        } else {
            List<AverageEntity> averageEntities = averageRepository.findAllByAverageId(memberRepository.findAllByMemberId(member));
            int averageId = averageEntities.stream()
                    .mapToInt(AverageEntity::getAverageId)
                    .max().getAsInt();
            averageEntity = averageRepository.findAllByAverageIdAndUserId(averageId, memberRepository.findAllByMemberId(member));
        }

        dataList.add((float) averageEntity.getAllSubjectDegree());
        dataList.add((float) averageEntity.getKemsoDegree());
        dataList.add((float) averageEntity.getKemsDegree());
        dataList.add((float) averageEntity.getKemrPercentile());
        dataList.add((float) averageEntity.getKemrDegree());

        // Specification을 사용하여 동적 쿼리 작성
        Specification<analysisEntity> spec = Specification.where(AnalysisSpecification.hasField(getLine.getField()))
                .and(AnalysisSpecification.hasMajor(getLine.getMajor()))
                .and(AnalysisSpecification.hasUniversity(getLine.getUniversity()))
                .and(AnalysisSpecification.hasKeyword(getLine.getKeyword()));

        List<analysisEntity> analysisEntities = analysisRepository.findAll(spec);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        analysisAll resultAnalysis = new analysisAll();


        for(analysisEntity entity:analysisEntities){
            List<Object> temp = new ArrayList<>();
            temp.add(entity.getUniversity());
            temp.add(entity.getDepartment());
            temp.add("종합");
            temp = Stream.concat(temp.stream(),dataList.stream())
                    .collect(Collectors.toList());
            
            HttpEntity<gpaData> httpRequest = new HttpEntity<>(new gpaData(temp), httpHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(url, httpRequest, String.class);
            if (response.getStatusCode() == HttpStatus.OK) { 
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode rootNode = mapper.readTree(response.getBody());
                    // "합격 확률" 키가 존재하는지 확인하고, 값이 있는지 확인
                    if (rootNode.has("합격 확률") && !rootNode.get("합격 확률").isNull()) {
                        String possibility = rootNode.get("합격 확률").asText();
                        resultAnalysis.data_list.add(new AnalysisDatas(entity.getUniversity(), entity.getDepartment(), possibility));
                    } else {
                        if (rootNode.has("error") && rootNode.get("error").asText().equals("Missing Data Error")) {
                            continue;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in JSON parsing");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultAnalysis);
    }
}