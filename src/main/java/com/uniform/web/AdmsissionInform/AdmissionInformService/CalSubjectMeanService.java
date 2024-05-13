package com.uniform.web.AdmsissionInform.AdmissionInformService;

import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.SubjectScore;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.WrappingSubjectScore;
import com.uniform.web.AdmsissionInform.Repository.AverageRepository;
import com.uniform.web.AdmsissionInform.Repository.ScoreRepository;
import com.uniform.web.AdmsissionInform.dto.ScoreDTO;
import com.uniform.web.AdmsissionInform.entity.AverageEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.special.Erf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalSubjectMeanService {
    private final AverageRepository averageRepository;

    public double sumAllScoreCredit(List<SubjectScore> subjectScores,int grade,double weight){
        if (subjectScores.stream().noneMatch(x->x.getSchoolYear() == grade)){
            return -1;
        }
        return (subjectScores.stream()
                .filter(x->x.getSchoolYear() == grade)
                .mapToDouble(x->x.getRawScore() * x.getCredit())
                .sum()/subjectScores.stream()
                .filter(x->x.getSchoolYear() == grade)
                .mapToInt(SubjectScore::getCredit)
                .sum())*weight;
    }
    public double sumSubjectScoredCredit(List<SubjectScore> subjectScores,int grade,double weight,String subject){
        List<String> subjects = Arrays.asList("국어","영어","수학");
        if (subject.equals("탐구")){
            subjects.add("과학");
            subjects.add("사회");
        }
        else {
            subjects.add(subject);
        }
        if (subjectScores.stream().noneMatch(x->x.getSchoolYear() == grade && subjects.contains(x.getCurriculum()))){
            return -1;
        }
        return (subjectScores.stream()
                .filter(x->x.getSchoolYear() == grade && subjects.contains(x.getCurriculum()))
                .mapToDouble(x->x.getRawScore() * x.getCredit())
                .sum()/subjectScores.stream()
                .filter(x->x.getSchoolYear() == grade && subjects.contains(x.getCurriculum()))
                .mapToInt(SubjectScore::getCredit)
                .sum())*weight;
    }
    public double calPercentile(List<SubjectScore> subjectScores){
        List<String> subject = Arrays.asList("국어","영어","수학","과학","사회");
        double result = 0.0;
        double first = 0.0;
        double second = 0.0;
        int count = 0;
        for (int i = 1; i < 4; i++) {
            first += compileData(subjectScores,i,1,subject)/2;
            second += compileData(subjectScores,i,2,subject)/2;
            if (first == 0.0){
                count -= 1;
                break;
            } else if (second == 0.0) {
                count += 1;
                result += first;
            }
            else{
                count += 1;
                result += (first+second)/2;
            }

        }
        return result/count;
    }
    public double compileData(List<SubjectScore> subjectScores,int grade,int term,List<String> subjects){
        if (subjectScores.stream()
                .noneMatch(x->subjects.contains(x.getCurriculum()) &&
                        x.getSchoolYear() == grade &&
                        x.getSchoolTerm() == term)){
                return -1;
        }
        return subjectScores.stream()
                .filter(x->subjects.contains(x.getCurriculum()) && x.getSchoolYear() == grade)
                .filter(x->x.getSchoolTerm() == term)
                .mapToDouble(x->calculatePercentile(x.getRawScore(),x.getSubjectMean(),x.getStandardDeviation()))
                .sum()/subjectScores.stream().filter(x->subjects.contains(x.getCurriculum())&&x.getSchoolYear()==grade).count();
    }
    public double calculatePercentile(double originalScore, double mean, double standardDeviation) {
        //표준 점수 계산
        double zScore = (originalScore - mean) / standardDeviation;
        //백분위 계산
        return cumulativeProbability(zScore);

    }
    public double cumulativeProbability(double x) {
        return (1.0 + Erf.erf(x / Math.sqrt(2.0))) / 2.0;
    }
    public int calAllSubject(WrappingSubjectScore wrappingSubjectScore){
        AverageEntity averageEntity = new AverageEntity();

        List<SubjectScore> subjectScores = wrappingSubjectScore.getScores();
        double allGrade = 0.0;
        //국영수탐 평균 결과 저장 변수
        double kemr = 0.0;
        //국영수과 평균 결과 저장 변수
        double kems = 0.0;
        //국영수사 평균 결과 저장 변수
        double kemso = 0.0;
        //국영수탐 백분위 결과 저장 변수
        double kemr_percentile = 0.0;
        //전과목
        int count = 1;
        double tmp = 0.0;
        for (int i = 1; i < 4; i++) {
            double weight = 0.0;
            if (i == 3){
                weight = 0.3;
            }
            else{
                weight = 0.4;
            }
            tmp = sumAllScoreCredit(subjectScores,i,weight);
            if (tmp == -1){
                break;
            }
            allGrade += tmp*weight;
            count += 1;
        }
        averageEntity.setAllSubjectDegree(allGrade/count);


        //국영수탐
        count = 1;
        tmp = 0.0;
        for (int i = 1; i < 4; i++) {
            double weight = 0.0;
            if (i == 3){
                weight = 0.3;
            }
            else{
                weight = 0.4;
            }
            tmp = sumSubjectScoredCredit(subjectScores,i,weight,"탐구");
            if (tmp == -1){
                break;
            }
            kemr += tmp*weight;
            count += 1;
        }
        averageEntity.setAllSubjectDegree(kemr/count);
        //국영수탐(백분위)
        averageEntity.setKemrPercentile(calPercentile(subjectScores));

        //국영수과
        count = 1;
        tmp = 0.0;
        for (int i = 1; i < 4; i++) {
            double weight = 0.0;
            if (i == 3){
                weight = 0.3;
            }
            else{
                weight = 0.4;
            }
            tmp = sumSubjectScoredCredit(subjectScores,i,weight,"과학");
            if (tmp == -1){
                break;
            }
            count += 1;
            kems += tmp * weight;
        }
        averageEntity.setAllSubjectDegree(kems/count);


        //국영수사
        count = 1;
        tmp = 0.0;
        for (int i = 1; i < 4; i++) {
            double weight = 0.0;
            if (i == 3){
                weight = 0.3;
            }
            else{
                weight = 0.4;
            }
            tmp = sumSubjectScoredCredit(subjectScores,i,weight,"사회");
            if (tmp == -1){
                break;
            }
            count += 1;
            kemso += tmp * weight;
        }
        averageEntity.setAllSubjectDegree(kemso/count);

        try{
            averageRepository.save(averageEntity);
        }
        catch (Exception e){
            return -1;
        }
        return 1;
    }



}
