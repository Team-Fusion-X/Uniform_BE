package com.uniform.web.AdmsissionInform.AdmissionInformService;
import com.uniform.web.AdmsissionInform.entity.SchoolInfo;
import com.uniform.web.AdmsissionInform.entity.gpaData;
import org.apache.commons.math3.special.Erf;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class AdmissionInformService {
    public gpaData calGPA(SchoolInfo schoolInfo) {
        //1학년 성적(1학기,2학기)
        List<List<SchoolInfo.SubjectScore>> allYearScores = new ArrayList<>();
        allYearScores.add(schoolInfo.getScores().getFirstYearFirstSemester());
        allYearScores.add(schoolInfo.getScores().getFirstYearSecondSemester());
        allYearScores.add(schoolInfo.getScores().getSecondYearFirstSemester());
        allYearScores.add(schoolInfo.getScores().getSecondYearSecondSemester());
        allYearScores.add(schoolInfo.getScores().getThirdYearFirstSemester());
        allYearScores.add(schoolInfo.getScores().getThirdYearSecondSemester());
        List<Object> data_list = new ArrayList<>();
        data_list.add(schoolInfo.getSchool());
        data_list.add(schoolInfo.getMajor());
        data_list.add(schoolInfo.getType());
        //전과목
        data_list.add(getAllSubjecGrade((allYearScores)));
        //국영수사
        data_list.add(getSubjectGrade(allYearScores,"사회"));
        //국영수과
        data_list.add(getSubjectGrade(allYearScores, "과학"));
        //국영수탐(백분위)
        data_list.add(calPercentile(allYearScores)*100);
        //국영수탐
       data_list.add(getSubjectGrade(allYearScores, "탐구"));
        gpaData data = new gpaData(data_list);
        return data;
    }

//        gpaData data = new gpaData();
//        data.setSchool(schoolInfo.getSchool());
//        data.setMajor(schoolInfo.getMajor());
//        SchoolInfo.Scores scores = new SchoolInfo.Scores();
//        scores = schoolInfo.getScores();
//        double weight = 0;
//        double res = 0;
//        double score = 0;
//        //국영수
//        res = 0;
//        weight = 0;
//        int[] all = {0, 1, 2, 3};
//        for (int i = 0; i < 4; i++) {
//            res += (scores.getFirstYearFirstSemester().get(all[i]).getRank() *
//                    scores.getFirstYearFirstSemester().get(all[i]).getCredits());
//            res += (scores.getFirstYearSecondSemester().get(all[i]).getRank() *
//                    scores.getFirstYearSecondSemester().get(all[i]).getCredits());
//            weight += scores.getFirstYearFirstSemester().get(all[i]).getCredits() +
//                    scores.getFirstYearSecondSemester().get(all[i]).getCredits();
//        }
//        for (int i = 0; i < 4; i++) {
//            res += (scores.getSecondYearFirstSemester().get(all[i]).getRank() *
//                    scores.getSecondYearFirstSemester().get(all[i]).getCredits());
//            res += (scores.getSecondYearSecondSemester().get(all[i]).getRank() *
//                    scores.getSecondYearSecondSemester().get(all[i]).getCredits());
//            weight += scores.getSecondYearFirstSemester().get(all[i]).getCredits() +
//                    scores.getSecondYearSecondSemester().get(all[i]).getCredits();
//        }
//        for (int i = 0; i < 4; i++) {
//            res += (scores.getThirdYearFirstSemester().get(all[i]).getRank() *
//                    scores.getThirdYearFirstSemester().get(all[i]).getCredits());
//            res += (scores.getThirdYearSecondSemester().get(all[i]).getRank() *
//                    scores.getThirdYearSecondSemester().get(all[i]).getCredits());
//            weight += scores.getThirdYearFirstSemester().get(all[i]).getCredits() +
//                    scores.getThirdYearSecondSemester().get(all[i]).getCredits();
//        }
//        //전과목
//        double allSubject = res;
//        double allSubjectWeight = weight;
//        for (int i = 4; i < 9; i++) {
//            allSubject += (scores.getFirstYearFirstSemester().get(i).getRank() *
//                    scores.getFirstYearFirstSemester().get(i).getCredits());
//            allSubject += (scores.getFirstYearSecondSemester().get(i).getRank() *
//                    scores.getFirstYearSecondSemester().get(i).getCredits());
//            allSubjectWeight += scores.getFirstYearFirstSemester().get(i).getCredits() +
//                    scores.getFirstYearSecondSemester().get(i).getCredits();
//        }
//        for (int i = 4; i < 9; i++) {
//            allSubject += (scores.getSecondYearFirstSemester().get(i).getRank() *
//                    scores.getSecondYearFirstSemester().get(i).getCredits());
//            allSubject += (scores.getSecondYearSecondSemester().get(i).getRank() *
//                    scores.getSecondYearSecondSemester().get(i).getCredits());
//            allSubjectWeight += scores.getSecondYearFirstSemester().get(i).getCredits() +
//                    scores.getSecondYearSecondSemester().get(i).getCredits();
//        }
//        for (int i = 4; i < 9; i++) {
//            allSubject += (scores.getThirdYearFirstSemester().get(i).getRank() *
//                    scores.getThirdYearFirstSemester().get(i).getCredits());
//            allSubject += (scores.getThirdYearSecondSemester().get(i).getRank() *
//                    scores.getThirdYearSecondSemester().get(i).getCredits());
//            allSubjectWeight += scores.getThirdYearFirstSemester().get(i).getCredits() +
//                    scores.getThirdYearSecondSemester().get(i).getCredits();
//        }
//        allSubject = allSubject / allSubjectWeight;
//        data.setAllSubjectAvg(allSubject);
//        //국영수과
//        double Science = res;
//        double ScienceWeight = weight;
//        Science += (scores.getFirstYearFirstSemester().get(6).getRank() *
//                scores.getFirstYearFirstSemester().get(6).getCredits());
//        Science += (scores.getFirstYearSecondSemester().get(6).getRank() *
//                scores.getFirstYearSecondSemester().get(6).getCredits());
//        Science += (scores.getSecondYearFirstSemester().get(6).getRank() *
//                scores.getSecondYearFirstSemester().get(6).getCredits());
//        Science += (scores.getSecondYearSecondSemester().get(6).getRank() *
//                scores.getSecondYearSecondSemester().get(6).getCredits());
//        Science += (scores.getThirdYearFirstSemester().get(6).getRank() *
//                scores.getThirdYearFirstSemester().get(6).getCredits());
//        Science += (scores.getThirdYearSecondSemester().get(6).getRank() *
//                scores.getThirdYearSecondSemester().get(6).getCredits());
//        ScienceWeight += scores.getFirstYearFirstSemester().get(6).getCredits() +
//                scores.getFirstYearSecondSemester().get(6).getCredits();
//        ScienceWeight += scores.getSecondYearFirstSemester().get(6).getCredits() +
//                scores.getSecondYearSecondSemester().get(6).getCredits();
//        ScienceWeight += scores.getThirdYearFirstSemester().get(6).getCredits() +
//                scores.getThirdYearSecondSemester().get(6).getCredits();
//        data.setScienceAvg(Science / ScienceWeight);
//        //국영수사
//        double Social = res;
//        double SocialWeight = weight;
//        int n = 4;
//        Social += (scores.getFirstYearFirstSemester().get(n).getRank() *
//                scores.getFirstYearFirstSemester().get(n).getCredits());
//        Social += (scores.getFirstYearSecondSemester().get(n).getRank() *
//                scores.getFirstYearSecondSemester().get(n).getCredits());
//        Social += (scores.getSecondYearFirstSemester().get(n).getRank() *
//                scores.getSecondYearFirstSemester().get(n).getCredits());
//        Social += (scores.getSecondYearSecondSemester().get(n).getRank() *
//                scores.getSecondYearSecondSemester().get(n).getCredits());
//        Social += (scores.getThirdYearFirstSemester().get(n).getRank() *
//                scores.getThirdYearFirstSemester().get(n).getCredits());
//        Social += (scores.getThirdYearSecondSemester().get(n).getRank() *
//                scores.getThirdYearSecondSemester().get(n).getCredits());
//        SocialWeight += scores.getFirstYearFirstSemester().get(n).getCredits() +
//                scores.getFirstYearSecondSemester().get(n).getCredits();
//        SocialWeight += scores.getSecondYearFirstSemester().get(n).getCredits() +
//                scores.getSecondYearSecondSemester().get(n).getCredits();
//        SocialWeight += scores.getThirdYearFirstSemester().get(n).getCredits() +
//                scores.getThirdYearSecondSemester().get(n).getCredits();
//
//        n = 5;
//        Social += (scores.getFirstYearSecondSemester().get(n).getRank() *
//                scores.getFirstYearSecondSemester().get(n).getCredits());
//        Social += (scores.getSecondYearFirstSemester().get(n).getRank() *
//                scores.getSecondYearFirstSemester().get(n).getCredits());
//        Social += (scores.getSecondYearSecondSemester().get(n).getRank() *
//                scores.getSecondYearSecondSemester().get(n).getCredits());
//        Social += (scores.getThirdYearFirstSemester().get(n).getRank() *
//                scores.getThirdYearFirstSemester().get(n).getCredits());
//        Social += (scores.getThirdYearSecondSemester().get(n).getRank()
//                * scores.getThirdYearSecondSemester().get(n).getCredits());
//        SocialWeight += scores.getFirstYearFirstSemester().get(n).getCredits() +
//                scores.getFirstYearSecondSemester().get(n).getCredits();
//        SocialWeight += scores.getSecondYearFirstSemester().get(n).getCredits() +
//                scores.getSecondYearSecondSemester().get(n).getCredits();
//        SocialWeight += scores.getThirdYearFirstSemester().get(n).getCredits() +
//                scores.getThirdYearSecondSemester().get(n).getCredits();
//        data.setSocialAvg(Social / SocialWeight);
//
//        //국영수탐
//        double research = Social;
//        double researchWeight = SocialWeight;
//        n = 6;
//        research += (scores.getFirstYearSecondSemester().get(n).getRank() *
//                scores.getFirstYearSecondSemester().get(n).getCredits());
//        research += (scores.getSecondYearFirstSemester().get(n).getRank() *
//                scores.getSecondYearFirstSemester().get(n).getCredits());
//        research += (scores.getSecondYearSecondSemester().get(n).getRank() *
//                scores.getSecondYearSecondSemester().get(n).getCredits());
//        research += (scores.getThirdYearFirstSemester().get(n).getRank() *
//                scores.getThirdYearFirstSemester().get(n).getCredits());
//        research += (scores.getThirdYearSecondSemester().get(n).getRank() *
//                scores.getThirdYearSecondSemester().get(n).getCredits());
//        researchWeight += scores.getFirstYearFirstSemester().get(n).getCredits() +
//                scores.getFirstYearSecondSemester().get(n).getCredits();
//        researchWeight += scores.getSecondYearFirstSemester().get(n).getCredits() +
//                scores.getSecondYearSecondSemester().get(n).getCredits();
//        researchWeight += scores.getThirdYearFirstSemester().get(n).getCredits() +
//                scores.getThirdYearSecondSemester().get(n).getCredits();
//        data.setResearchAvg(research / ScienceWeight);
//
//        //국영수탐 백분율
//        //국어 백분율
//        double First;
//        double Second;
//        double KoreanAvg;
//        double MathAvg;
//        double SocialAvg;
//        double ScienceAvg;
//        double FirstGrade;
//        double SecondGrade;
//        double ThirdGrade;
//        double EnglishAvg;
//        double ResearchAvg;
//
//        First = calculatePercentile(scores.getFirstYearFirstSemester().get(0).getRawScore(),scores.getFirstYearFirstSemester().get(0).getSubjectAverage(),scores.getFirstYearFirstSemester().get(0).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearFirstSemester().get(1).getRawScore(),scores.getFirstYearFirstSemester().get(1).getSubjectAverage(),scores.getFirstYearFirstSemester().get(1).getStandardD());
//        double result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getFirstYearSecondSemester().get(0).getRawScore(),scores.getFirstYearSecondSemester().get(0).getSubjectAverage(),scores.getFirstYearSecondSemester().get(0).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(1).getRawScore(),scores.getFirstYearSecondSemester().get(1).getSubjectAverage(),scores.getFirstYearSecondSemester().get(1).getStandardD());
//        result += (First+Second)/2;
//        FirstGrade = result/2;
//
//
//        First = calculatePercentile(scores.getSecondYearFirstSemester().get(0).getRawScore(),scores.getSecondYearFirstSemester().get(0).getSubjectAverage(),scores.getSecondYearFirstSemester().get(0).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearFirstSemester().get(1).getRawScore(),scores.getSecondYearFirstSemester().get(1).getSubjectAverage(),scores.getSecondYearFirstSemester().get(1).getStandardD());
//        result =(First+Second)/2;
//
//
//        First = calculatePercentile(scores.getSecondYearSecondSemester().get(0).getRawScore(),scores.getSecondYearSecondSemester().get(0).getSubjectAverage(),scores.getSecondYearSecondSemester().get(0).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(1).getRawScore(),scores.getSecondYearSecondSemester().get(1).getSubjectAverage(),scores.getSecondYearSecondSemester().get(1).getStandardD());
//        result += (First+Second)/2;
//        SecondGrade = result/2;
//
//
//        First = calculatePercentile(scores.getThirdYearFirstSemester().get(0).getRawScore(),scores.getThirdYearFirstSemester().get(0).getSubjectAverage(),scores.getThirdYearFirstSemester().get(0).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearFirstSemester().get(1).getRawScore(),scores.getThirdYearFirstSemester().get(1).getSubjectAverage(),scores.getThirdYearFirstSemester().get(1).getStandardD());
//        result = (First+Second)/2;
//
//
//
//        First = calculatePercentile(scores.getThirdYearSecondSemester().get(0).getRawScore(),scores.getThirdYearSecondSemester().get(0).getSubjectAverage(),scores.getThirdYearSecondSemester().get(0).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(1).getRawScore(),scores.getThirdYearSecondSemester().get(1).getSubjectAverage(),scores.getThirdYearSecondSemester().get(1).getStandardD());
//        result += (First+Second)/2;
//
//        ThirdGrade = result/2;
//        KoreanAvg = (FirstGrade+SecondGrade+ThirdGrade)/3;
//
//
//        //수학
//
//        First = calculatePercentile(scores.getFirstYearFirstSemester().get(2).getRawScore(),scores.getFirstYearFirstSemester().get(2).getSubjectAverage(),scores.getFirstYearFirstSemester().get(2).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearFirstSemester().get(3).getRawScore(),scores.getFirstYearFirstSemester().get(3).getSubjectAverage(),scores.getFirstYearFirstSemester().get(3).getStandardD());
//        result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getFirstYearSecondSemester().get(2).getRawScore(),scores.getFirstYearSecondSemester().get(2).getSubjectAverage(),scores.getFirstYearSecondSemester().get(2).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(3).getRawScore(),scores.getFirstYearSecondSemester().get(3).getSubjectAverage(),scores.getFirstYearSecondSemester().get(3).getStandardD());
//        result += (First+Second)/2;
//        FirstGrade = result/2;
//
//
//        First = calculatePercentile(scores.getSecondYearFirstSemester().get(2).getRawScore(),scores.getSecondYearFirstSemester().get(2).getSubjectAverage(),scores.getSecondYearFirstSemester().get(2).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearFirstSemester().get(3).getRawScore(),scores.getSecondYearFirstSemester().get(3).getSubjectAverage(),scores.getSecondYearFirstSemester().get(3).getStandardD());
//        result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getSecondYearSecondSemester().get(2).getRawScore(),scores.getSecondYearSecondSemester().get(2).getSubjectAverage(),scores.getSecondYearSecondSemester().get(2).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(3).getRawScore(),scores.getSecondYearSecondSemester().get(3).getSubjectAverage(),scores.getSecondYearSecondSemester().get(3).getStandardD());
//        result += (First+Second)/2;
//        SecondGrade = result/2;
//
//
//        First = calculatePercentile(scores.getThirdYearFirstSemester().get(2).getRawScore(),scores.getThirdYearFirstSemester().get(2).getSubjectAverage(),scores.getThirdYearFirstSemester().get(2).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearFirstSemester().get(3).getRawScore(),scores.getThirdYearFirstSemester().get(3).getSubjectAverage(),scores.getThirdYearFirstSemester().get(3).getStandardD());
//        result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getThirdYearSecondSemester().get(2).getRawScore(),scores.getThirdYearSecondSemester().get(2).getSubjectAverage(),scores.getThirdYearSecondSemester().get(2).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(3).getRawScore(),scores.getThirdYearSecondSemester().get(3).getSubjectAverage(),scores.getThirdYearSecondSemester().get(3).getStandardD());
//        result += (First+Second)/2;
//        ThirdGrade = result/2;
//
//        MathAvg = (FirstGrade+SecondGrade+ThirdGrade)/3;
//
//
//        //사탐
//        int sub = 5;
//        int sub2 = 6;
//        First = calculatePercentile(scores.getFirstYearFirstSemester().get(sub).getRawScore(),scores.getFirstYearFirstSemester().get(sub).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearFirstSemester().get(sub2).getRawScore(),scores.getFirstYearFirstSemester().get(sub2).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub2).getStandardD());
//        result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getFirstYearSecondSemester().get(sub).getRawScore(),scores.getFirstYearSecondSemester().get(sub).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(sub2).getRawScore(),scores.getFirstYearSecondSemester().get(sub2).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub2).getStandardD());
//        result += (First+Second)/2;
//        FirstGrade = result/2;
//
//
//        First = calculatePercentile(scores.getSecondYearFirstSemester().get(sub).getRawScore(),scores.getSecondYearFirstSemester().get(sub).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearFirstSemester().get(sub2).getRawScore(),scores.getSecondYearFirstSemester().get(sub2).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub2).getStandardD());
//        result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getSecondYearSecondSemester().get(sub).getRawScore(),scores.getSecondYearSecondSemester().get(sub).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(sub2).getRawScore(),scores.getSecondYearSecondSemester().get(sub2).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub2).getStandardD());
//        result += (First+Second)/2;
//        SecondGrade = result/2;
//
//
//        First = calculatePercentile(scores.getThirdYearFirstSemester().get(sub).getRawScore(),scores.getThirdYearFirstSemester().get(sub).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearFirstSemester().get(sub2).getRawScore(),scores.getThirdYearFirstSemester().get(sub2).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub2).getStandardD());
//        result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getThirdYearSecondSemester().get(sub).getRawScore(),scores.getThirdYearSecondSemester().get(sub).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(sub2).getRawScore(),scores.getThirdYearSecondSemester().get(sub2).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub2).getStandardD());
//        result += (First+Second)/2;
//        ThirdGrade = result/2;
//
//        SocialAvg = (FirstGrade+SecondGrade+ThirdGrade)/3;
//        //영어
//        sub = 4;
//        First = calculatePercentile(scores.getFirstYearFirstSemester().get(sub).getRawScore(),scores.getFirstYearFirstSemester().get(sub).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(sub).getRawScore(),scores.getFirstYearSecondSemester().get(sub).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub).getStandardD());
//        result = (First+Second)/2;
//
//        First = calculatePercentile(scores.getSecondYearFirstSemester().get(sub).getRawScore(),scores.getSecondYearFirstSemester().get(sub).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(sub).getRawScore(),scores.getSecondYearSecondSemester().get(sub).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub).getStandardD());
//        result += (First+Second)/2;
//
//        First = calculatePercentile(scores.getThirdYearFirstSemester().get(sub).getRawScore(),scores.getThirdYearFirstSemester().get(sub).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(sub).getRawScore(),scores.getThirdYearSecondSemester().get(sub).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub).getStandardD());
//        result += (First+Second)/2;
//
//        EnglishAvg = result/3;
//
//
//        //과학
//        sub = 7;
//        First = calculatePercentile(scores.getFirstYearFirstSemester().get(sub).getRawScore(),scores.getFirstYearFirstSemester().get(sub).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(sub).getRawScore(),scores.getFirstYearSecondSemester().get(sub).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub).getStandardD());
//        result = (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getSecondYearFirstSemester().get(sub).getRawScore(),scores.getSecondYearFirstSemester().get(sub).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(sub).getRawScore(),scores.getSecondYearSecondSemester().get(sub).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub).getStandardD());
//        result += (First+Second)/2;
//
//
//        First = calculatePercentile(scores.getThirdYearFirstSemester().get(sub).getRawScore(),scores.getThirdYearFirstSemester().get(sub).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub).getStandardD());
//        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(sub).getRawScore(),scores.getThirdYearSecondSemester().get(sub).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub).getStandardD());
//        result += (First+Second)/2;
//
//        ScienceAvg = result/3;
//
//        ResearchAvg = (ScienceAvg+SocialAvg)/2;
//
//        data.setPrecen((KoreanAvg+EnglishAvg+MathAvg+ResearchAvg)-220);
//
//        return data;


//    }
    //국어 영어 수학 탐구 과목의 백분위를 반환하는 함수
    public static double calPercentile(List<List<SchoolInfo.SubjectScore>> subjects){
        double tmp = 0.0;
        double tmp2 = 0.0;
        HashMap<String,Double> subjectsAvgs = new HashMap<>();
        String[] subjectName = {"국어","영어","수학","탐구"};
        for (String s : subjectName) {
            subjectsAvgs.put(s, 0.0);
        }
        for (int i = 0; i < subjects.size(); i+=2) {
            //1학기
            for (String s : subjectName) {
                tmp = 0.0;
                int count = 0;
                List<Double>  originalScore = subjects.get(i).stream()
                        .filter(x -> x.getCurriculum().contains(s))
                        .map(x -> x.getRawScore())
                        .collect(Collectors.toList());
                List<Double> means = subjects.get(i).stream()
                        .filter(x -> x.getCurriculum().contains(s))
                        .map(x -> x.getSubjectAverage())
                        .collect(Collectors.toList());
                List<Double> standardDeviation = subjects.get(i).stream()
                        .filter(x -> x.getCurriculum().contains(s))
                        .map(x -> x.getStandardD())
                        .collect(Collectors.toList());
                for (int j = 0; j < originalScore.size(); j++) {
                    count += 1;
                    tmp += calculatePercentile(originalScore.get(j),means.get(j),standardDeviation.get(j));
                }
                tmp2 = tmp/count;
                tmp = 0.0;
                count = 0;
                originalScore = subjects.get(i+1).stream()
                        .filter(x -> x.getCurriculum().contains(s))
                        .map(x -> x.getRawScore())
                        .collect(Collectors.toList());
                means = subjects.get(i+1).stream()
                        .filter(x -> x.getCurriculum().contains(s))
                        .map(x -> x.getSubjectAverage())
                        .collect(Collectors.toList());
                standardDeviation = subjects.get(i+1).stream()
                        .filter(x -> x.getCurriculum().contains(s))
                        .map(x -> x.getStandardD())
                        .collect(Collectors.toList());
                for (int j = 0; j < originalScore.size(); j++) {
                    count += 1;
                    tmp2 += calculatePercentile(originalScore.get(j), means.get(j), standardDeviation.get(j));
                }
                double value = subjectsAvgs.get(s);
                subjectsAvgs.put(s,value+((tmp+tmp2)/2));
            }
        }
        double result = 0.0;
        for (String s: subjectName) {
            if (Double.isNaN(subjectsAvgs.get(s)/3)){
                continue;
            }
            result += subjectsAvgs.get(s)/3;
        }
        return (result);
    }



    public static double calculatePercentile(double originalScore, double mean, double standardDeviation) {
        //표준 점수 계산
        double zScore = (originalScore - mean) / standardDeviation;
        //백분위 계산
        double percentile = cumulativeProbability(zScore);
        return percentile;
    }
    public static double cumulativeProbability(double x) {
        return (1.0 + Erf.erf(x / Math.sqrt(2.0))) / 2.0;
    }
    //전과목 반환하는 함수
    public static double getAllSubjecGrade(List<List<SchoolInfo.SubjectScore>> subjects){
        //모든 학년 학기 (단위수 * 등급)
//        List<Integer> allGrades = new ArrayList<>();
//        List<Double> result = new ArrayList<>();
//        Deque<Integer> Credits = new ArrayDeque<>();
//        int creditCount = 0;
//        int tmp = 0;
//        double allSubjectGrade = 0;
//        for (int i = 0; i < subjects.size(); i++) {
//            tmp = 0;
//            creditCount = 0;
//            for (int j = 0; j < subjects.get(i).size(); j++) {
//                tmp += (subjects.get(i).get(j).getCredits()*subjects.get(i).get(j).getRank());
//                creditCount += subjects.get(i).get(j).getCredits();
//            }
//             allGrades.add(tmp);
//             Credits.add(creditCount);
//        }
//        for (int i = 0; i < allGrades.size(); i+=2) {
//            result.add((double) (allGrades.get(i)+allGrades.get(i+1))/Credits.removeFirst());
//        }
//        for (int i = 0; i < result.size(); i++) {
//            if (i == 0){
//                allSubjectGrade += result.get(i)*0.3;
//            } else if (i == 1) {
//                allSubjectGrade += result.get(i)*0.3;
//            }
//            else{
//                allSubjectGrade += result.get(i)*0.4;
//            }
//        }
//        return allSubjectGrade;
        double result = 0;
        double[] weights = {0.3,0.3,0.4};
        //모든 학년 평균 등급을 저장하는 리스트
        List<Double> avgs = new ArrayList<>();
        //각 학년 총 단위 수를 저장할 변수
        int creditCount = 0;
        //각 학년의 각과목 (과목 * 등급) 합한 값을 더하고 저장할 임시 변수
        double tmp = 0;
        for (int i = 0; i < subjects.size(); i+=2) {
            tmp = 0;
            creditCount = 0;
            tmp += subjects.get(i).stream().mapToInt(x -> x.getCredits() * x.getRank()).sum();
            tmp += subjects.get(i + 1).stream().mapToInt(x->x.getRank() * x.getRank()).sum();

            creditCount += subjects.get(i).stream().mapToInt(x -> x.getCredits()).sum();
            creditCount += subjects.get(i+1).stream().mapToInt(x -> x.getCredits()).sum();

            avgs.add(tmp / creditCount);
        }
        for (int i = 0; i < 3; i++) {
            result += avgs.get(i) * weights[i];
        }
        return result;
    }
    //국어,영어,수학, ??(매개변수) 평균등급 산출하는 함수 ?? 값 -> (과학,탐구,사회)
    public static double getSubjectGrade(List<List<SchoolInfo.SubjectScore>> subjects,String subject){
        //모든 학년 비율에 따라 등급 계산 후 저장할 변수 즉, 최종 반환할 변수
        double result = 0;
        double[] weights = {0.3,0.3,0.4};
        //모든 학년의 평균을 저장하는 리스트
        List<Double> avgs = new ArrayList<>();
        //각 학년의 총 단위 수를 저장할 변수
        int creditCount = 0;
        //각 학년의 총 (해당 과목 단위수 * 등급) 합을 저장할 임시 변수
        int tmp = 0;
        for (int i = 0; i < subjects.size(); i+=2) {
            tmp = 0;
            creditCount = 0;
            tmp += subjects.get(i).stream()
                    .filter(x -> x.getCurriculum().contains(subject) || x.getCurriculum().contains("영어")
                            || x.getCurriculum().contains("수학") || x.getCurriculum().contains("국어"))
                    .mapToInt(x -> x.getCredits() * x.getRank())
                    .sum();
            tmp += subjects.get(i+1).stream()
                    .filter(x -> x.getCurriculum().contains(subject) ||
                            x.getCurriculum().contains("국어") ||
                            x.getCurriculum().contains("수학") ||
                            x.getCurriculum().contains("영어"))
                    .mapToInt((x -> (x.getCredits() * x.getRank())))
                    .sum();
            creditCount += subjects.get(i).stream()
                    .filter(x -> x.getCurriculum().contains("국어") || x.getCurriculum().contains("영어") ||
                            x.getCurriculum().contains("수학") || x.getCurriculum().contains(subject))
                    .mapToInt(SchoolInfo.SubjectScore::getCredits)
                    .sum();
            creditCount += subjects.get(i + 1).stream()
                    .filter(x -> x.getCurriculum().contains("국어") ||
                            x.getCurriculum().contains("영어") ||
                            x.getCurriculum().contains("수학") ||
                            x.getCurriculum().contains(subject))
                    .mapToInt(SchoolInfo.SubjectScore::getCredits)
                    .sum();
            avgs.add((double) tmp / creditCount);
        }
        for (int i = 0; i < 3; i++) {
            result += (avgs.get(i)*weights[i]);
        }
        return (result);

    }


}
