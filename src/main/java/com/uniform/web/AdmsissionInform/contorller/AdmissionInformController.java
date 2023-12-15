package com.uniform.web.AdmsissionInform.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniform.web.AdmsissionInform.entity.AnalysisData;
import com.uniform.web.AdmsissionInform.entity.SchoolInfo;
import com.uniform.web.AdmsissionInform.entity.gpaData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdmissionInformController {

//    @PostMapping("/analysis")
//    public SchoolInfo checkInform(@RequestBody SchoolInfo schoolInfo) {
//        return null;
//    }
    @PostMapping("/analysis")
    public SchoolInfo.Scores checkInform(@RequestBody SchoolInfo schoolInfo){

        if (schoolInfo != null &&
                schoolInfo.getScores() != null &&
                schoolInfo.getScores().getFirstYearFirstSemester() != null &&
                schoolInfo.getScores().getFirstYearFirstSemester().size() > 1) {
            System.out.println(schoolInfo.getScores().getFirstYearFirstSemester().get(1).getCurriculum());
            // 안전하게 접근
            return schoolInfo.getScores();
        } else {
            // 처리할 수 없는 경우에 대한 로직 추가
            return null;
        }
}
    @PostMapping("/analysis/gpa/test")
    public gpaData gpatest(){
        gpaData gpaData = new gpaData();
        gpaData.setMajor("컴공과");
        gpaData.setSchool("개쩌는 학교");
        gpaData.setAllSubjectAvg(1.11);
        gpaData.setPrecen(1.2);
        gpaData.setResearchAvg(1.2);
        gpaData.setScienceAvg(1.222);
        return gpaData;
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

}