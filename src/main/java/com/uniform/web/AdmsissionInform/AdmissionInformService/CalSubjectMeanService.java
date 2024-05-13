package com.uniform.web.AdmsissionInform.AdmissionInformService;

import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.SubjectScore;
import com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson.WrappingSubjectScore;
import com.uniform.web.AdmsissionInform.Repository.AverageRepository;
import com.uniform.web.AdmsissionInform.Repository.ScoreRepository;
import com.uniform.web.AdmsissionInform.dto.ScoreDTO;
import com.uniform.web.AdmsissionInform.entity.AverageEntity;
import com.uniform.web.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.special.Erf;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .mapToDouble(x->x.getCredit() * x.getRanking())
                .sum()/subjectScores.stream()
                .filter(x->x.getSchoolYear() == grade)
                .mapToInt(SubjectScore::getCredit)
                .sum())*weight;
    }
    public double sumSubjectScoredCredit(List<SubjectScore> subjectScores,int grade,double weight,String subject){
        List<String> subjects = Arrays.asList("국어","영어","수학");
        List<String> modifiedSubject1 = new ArrayList<>(subjects);
        if (subject.equals("탐구")){
            modifiedSubject1.add("과학");
            modifiedSubject1.add("사회");
        }
        else {
            modifiedSubject1.add(subject);
        }
        if (subjectScores.stream().noneMatch(x->x.getSchoolYear() == grade && modifiedSubject1.contains(x.getCurriculum()))){
            return -1;
        }
        double creditScore = subjectScores.stream()
                .filter(x->x.getSchoolYear() == grade && modifiedSubject1.contains(x.getCurriculum()))
                .mapToDouble(x->x.getCredit() * x.getRanking())
                .sum();
        double creditSum = subjectScores.stream()
                .filter(x->x.getSchoolYear() == grade && modifiedSubject1.contains(x.getCurriculum()))
                .mapToInt(SubjectScore::getCredit)
                .sum();
        return (creditScore/creditSum) * weight;
    }
    public double calPercentile(List<SubjectScore> subjectScores){
        List<String> subject = Arrays.asList("국어","영어","수학","과학","사회");
        double result = 0.0;
        double first = 0.0;
        double second = 0.0;
        int count = 0;
        Stream<SubjectScore> subjectScoreStream = subjectScores.stream().filter(x->subject.contains(x.getCurriculum()));
        for (int i = 1; i < 4; i++) {
            first += compileData(subjectScores,i,1,subject)/2;
            second += compileData(subjectScores,i,2,subject)/2;
            if (first == 0.0){
                break;
            } else if (second == 0.0) {
                result += first;
                break;
            }
            else{
                result += (first+second)/2;
            }
        }
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 1).count() != 0){
            count += 1;
        }
        subjectScoreStream = subjectScores.stream().filter(x->subject.contains(x.getCurriculum()));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 2).count() != 0) {
            count += 1;
        }
        subjectScoreStream = subjectScores.stream().filter(x->subject.contains(x.getCurriculum()));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 3).count() != 0) {
            count += 1;
        }

        return (result/count)*100;
    }
    public double compileData(List<SubjectScore> subjectScores,int grade,int term,List<String> subjects){
        return subjectScores.stream()
                .filter(x->subjects.contains(x.getCurriculum()) && x.getSchoolYear() == grade)
                .filter(x->x.getSchoolTerm() == term)
                .mapToDouble(x->calculatePercentile(x.getRawScore(),x.getSubjectMean(),x.getSdeviation()))
                .sum();
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
    public int calAllSubject(WrappingSubjectScore wrappingSubjectScore, MemberEntity memberEntity){
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
        double count = 0.0;
        double tmp = 0.0;
        for (int i = 1; i < 4; i++) {
            double weight = 0.0;
            if (i == 3){
                weight = 0.4;
            }
            else{
                weight = 0.3;
            }
            tmp = sumAllScoreCredit(subjectScores,i,weight);
            if (tmp == -1){
                break;
            }
            allGrade += tmp;
        }
        if (subjectScores.stream().filter(x->x.getSchoolYear() == 1).count() != 0){
            count += 1;
        }
        if (subjectScores.stream().filter(x->x.getSchoolYear() == 2).count() != 0) {
            count+=1;
        }
        if (subjectScores.stream().filter(x->x.getSchoolYear() == 3).count() != 0) {
            count+=1;
        }
        averageEntity.setAllSubjectDegree(allGrade/count);

        //국영수탐
        Stream<SubjectScore> subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("과학") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학")||x.getCurriculum().equals("사회"));
        count = 0.0;
        tmp = 0.0;
        for (int i = 1; i < 4; i++) {
            double weight = 0.0;
            if (i == 3){
                weight = 0.4;
            }
            else{
                weight = 0.3;
            }
            tmp = sumSubjectScoredCredit(subjectScores,i,weight,"탐구");
            if (tmp == -1){
                break;
            }
            kemr += tmp;
        }
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 1).count() != 0){
            count += 1;
        }
        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("과학") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학")||x.getCurriculum().equals("사회"));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 2).count() != 0) {
            count+=1;
        }
        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("과학") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학")||x.getCurriculum().equals("사회"));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 3).count() != 0) {
            count+=1;
        }
        averageEntity.setKemrDegree(kemr/count);
        //국영수탐(백분위)
        tmp = calPercentile(subjectScores);
        averageEntity.setKemrPercentile(tmp);

        //국영수과
        count = 0.0;
        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("과학") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학"));
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
            kems += tmp;
        }
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 1).count() != 0){
            count += 1;
        }
        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("과학") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학"));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 2).count() != 0) {
            count+=1;
        }
        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("과학") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학"));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 3).count() != 0) {
            count+=1;
        }
        kems /= count;
        averageEntity.setKemsDegree(kems);

        //국영수사
        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("사회") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학"));
        count = 0;
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
            kemso += tmp;
        }

        if (subjectScoreStream.filter(x->x.getSchoolYear() == 1).count() != 0){
            count += 1;
        }

        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("사회") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학"));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 2).count() != 0) {
            count+=1;
        }

        subjectScoreStream = subjectScores.stream().filter(x -> x.getCurriculum().equals("사회") || x.getCurriculum().equals("국어") ||
                x.getCurriculum().equals("영어") || x.getCurriculum().equals("수학"));
        if (subjectScoreStream.filter(x->x.getSchoolYear() == 3).count() != 0) {
            count+=1;
        }

        averageEntity.setKemsoDegree(kemso/count);
        averageEntity.setUserId(memberEntity);
        try{
            averageRepository.save(averageEntity);
        }
        catch (Exception e){
            return -1;
        }
        return 1;
    }



}
