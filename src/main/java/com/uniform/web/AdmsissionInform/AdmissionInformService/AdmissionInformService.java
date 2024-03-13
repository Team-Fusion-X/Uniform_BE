package com.uniform.web.AdmsissionInform.AdmissionInformService;
import com.uniform.web.AdmsissionInform.entity.AnalysisData;
import com.uniform.web.AdmsissionInform.entity.SchoolInfo;
import com.uniform.web.AdmsissionInform.entity.gpaData;
import org.apache.commons.math3.*;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.List;

public class AdmissionInformService {
    public gpaData calGPA(SchoolInfo schoolInfo) {
        gpaData data = new gpaData();
        data.setSchool(schoolInfo.getSchool());
        data.setMajor(schoolInfo.getMajor());
        SchoolInfo.Scores scores = new SchoolInfo.Scores();
        scores = schoolInfo.getScores();
        double weight = 0;
        double res = 0;
        double score = 0;
        //국영수
        res = 0;
        weight = 0;
        int[] all = {0, 1, 2, 3};
        for (int i = 0; i < 4; i++) {
            res += (scores.getFirstYearFirstSemester().get(all[i]).getRank() *
                    scores.getFirstYearFirstSemester().get(all[i]).getCredits());
            res += (scores.getFirstYearSecondSemester().get(all[i]).getRank() *
                    scores.getFirstYearSecondSemester().get(all[i]).getCredits());
            weight += scores.getFirstYearFirstSemester().get(all[i]).getCredits() +
                    scores.getFirstYearSecondSemester().get(all[i]).getCredits();
        }
        for (int i = 0; i < 4; i++) {
            res += (scores.getSecondYearFirstSemester().get(all[i]).getRank() *
                    scores.getSecondYearFirstSemester().get(all[i]).getCredits());
            res += (scores.getSecondYearSecondSemester().get(all[i]).getRank() *
                    scores.getSecondYearSecondSemester().get(all[i]).getCredits());
            weight += scores.getSecondYearFirstSemester().get(all[i]).getCredits() +
                    scores.getSecondYearSecondSemester().get(all[i]).getCredits();
        }
        for (int i = 0; i < 4; i++) {
            res += (scores.getThirdYearFirstSemester().get(all[i]).getRank() *
                    scores.getThirdYearFirstSemester().get(all[i]).getCredits());
            res += (scores.getThirdYearSecondSemester().get(all[i]).getRank() *
                    scores.getThirdYearSecondSemester().get(all[i]).getCredits());
            weight += scores.getThirdYearFirstSemester().get(all[i]).getCredits() +
                    scores.getThirdYearSecondSemester().get(all[i]).getCredits();
        }
        //전과목
        double allSubject = res;
        double allSubjectWeight = weight;
        for (int i = 4; i < 9; i++) {
            allSubject += (scores.getFirstYearFirstSemester().get(i).getRank() *
                    scores.getFirstYearFirstSemester().get(i).getCredits());
            allSubject += (scores.getFirstYearSecondSemester().get(i).getRank() *
                    scores.getFirstYearSecondSemester().get(i).getCredits());
            allSubjectWeight += scores.getFirstYearFirstSemester().get(i).getCredits() +
                    scores.getFirstYearSecondSemester().get(i).getCredits();
        }
        for (int i = 4; i < 9; i++) {
            allSubject += (scores.getSecondYearFirstSemester().get(i).getRank() *
                    scores.getSecondYearFirstSemester().get(i).getCredits());
            allSubject += (scores.getSecondYearSecondSemester().get(i).getRank() *
                    scores.getSecondYearSecondSemester().get(i).getCredits());
            allSubjectWeight += scores.getSecondYearFirstSemester().get(i).getCredits() +
                    scores.getSecondYearSecondSemester().get(i).getCredits();
        }
        for (int i = 4; i < 9; i++) {
            allSubject += (scores.getThirdYearFirstSemester().get(i).getRank() *
                    scores.getThirdYearFirstSemester().get(i).getCredits());
            allSubject += (scores.getThirdYearSecondSemester().get(i).getRank() *
                    scores.getThirdYearSecondSemester().get(i).getCredits());
            allSubjectWeight += scores.getThirdYearFirstSemester().get(i).getCredits() +
                    scores.getThirdYearSecondSemester().get(i).getCredits();
        }
        allSubject = allSubject / allSubjectWeight;
        data.setAllSubjectAvg(allSubject);
        //국영수과
        double Science = res;
        double ScienceWeight = weight;
        Science += (scores.getFirstYearFirstSemester().get(6).getRank() *
                scores.getFirstYearFirstSemester().get(6).getCredits());
        Science += (scores.getFirstYearSecondSemester().get(6).getRank() *
                scores.getFirstYearSecondSemester().get(6).getCredits());
        Science += (scores.getSecondYearFirstSemester().get(6).getRank() *
                scores.getSecondYearFirstSemester().get(6).getCredits());
        Science += (scores.getSecondYearSecondSemester().get(6).getRank() *
                scores.getSecondYearSecondSemester().get(6).getCredits());
        Science += (scores.getThirdYearFirstSemester().get(6).getRank() *
                scores.getThirdYearFirstSemester().get(6).getCredits());
        Science += (scores.getThirdYearSecondSemester().get(6).getRank() *
                scores.getThirdYearSecondSemester().get(6).getCredits());
        ScienceWeight += scores.getFirstYearFirstSemester().get(6).getCredits() +
                scores.getFirstYearSecondSemester().get(6).getCredits();
        ScienceWeight += scores.getSecondYearFirstSemester().get(6).getCredits() +
                scores.getSecondYearSecondSemester().get(6).getCredits();
        ScienceWeight += scores.getThirdYearFirstSemester().get(6).getCredits() +
                scores.getThirdYearSecondSemester().get(6).getCredits();
        data.setScienceAvg(Science / ScienceWeight);
        //국영수사
        double Social = res;
        double SocialWeight = weight;
        int n = 4;
        Social += (scores.getFirstYearFirstSemester().get(n).getRank() *
                scores.getFirstYearFirstSemester().get(n).getCredits());
        Social += (scores.getFirstYearSecondSemester().get(n).getRank() *
                scores.getFirstYearSecondSemester().get(n).getCredits());
        Social += (scores.getSecondYearFirstSemester().get(n).getRank() *
                scores.getSecondYearFirstSemester().get(n).getCredits());
        Social += (scores.getSecondYearSecondSemester().get(n).getRank() *
                scores.getSecondYearSecondSemester().get(n).getCredits());
        Social += (scores.getThirdYearFirstSemester().get(n).getRank() *
                scores.getThirdYearFirstSemester().get(n).getCredits());
        Social += (scores.getThirdYearSecondSemester().get(n).getRank() *
                scores.getThirdYearSecondSemester().get(n).getCredits());
        SocialWeight += scores.getFirstYearFirstSemester().get(n).getCredits() +
                scores.getFirstYearSecondSemester().get(n).getCredits();
        SocialWeight += scores.getSecondYearFirstSemester().get(n).getCredits() +
                scores.getSecondYearSecondSemester().get(n).getCredits();
        SocialWeight += scores.getThirdYearFirstSemester().get(n).getCredits() +
                scores.getThirdYearSecondSemester().get(n).getCredits();

        n = 5;
        Social += (scores.getFirstYearSecondSemester().get(n).getRank() *
                scores.getFirstYearSecondSemester().get(n).getCredits());
        Social += (scores.getSecondYearFirstSemester().get(n).getRank() *
                scores.getSecondYearFirstSemester().get(n).getCredits());
        Social += (scores.getSecondYearSecondSemester().get(n).getRank() *
                scores.getSecondYearSecondSemester().get(n).getCredits());
        Social += (scores.getThirdYearFirstSemester().get(n).getRank() *
                scores.getThirdYearFirstSemester().get(n).getCredits());
        Social += (scores.getThirdYearSecondSemester().get(n).getRank()
                * scores.getThirdYearSecondSemester().get(n).getCredits());
        SocialWeight += scores.getFirstYearFirstSemester().get(n).getCredits() +
                scores.getFirstYearSecondSemester().get(n).getCredits();
        SocialWeight += scores.getSecondYearFirstSemester().get(n).getCredits() +
                scores.getSecondYearSecondSemester().get(n).getCredits();
        SocialWeight += scores.getThirdYearFirstSemester().get(n).getCredits() +
                scores.getThirdYearSecondSemester().get(n).getCredits();
        data.setSocialAvg(Social / SocialWeight);

        //국영수탐
        double research = Social;
        double researchWeight = SocialWeight;
        n = 6;
        research += (scores.getFirstYearSecondSemester().get(n).getRank() *
                scores.getFirstYearSecondSemester().get(n).getCredits());
        research += (scores.getSecondYearFirstSemester().get(n).getRank() *
                scores.getSecondYearFirstSemester().get(n).getCredits());
        research += (scores.getSecondYearSecondSemester().get(n).getRank() *
                scores.getSecondYearSecondSemester().get(n).getCredits());
        research += (scores.getThirdYearFirstSemester().get(n).getRank() *
                scores.getThirdYearFirstSemester().get(n).getCredits());
        research += (scores.getThirdYearSecondSemester().get(n).getRank() *
                scores.getThirdYearSecondSemester().get(n).getCredits());
        researchWeight += scores.getFirstYearFirstSemester().get(n).getCredits() +
                scores.getFirstYearSecondSemester().get(n).getCredits();
        researchWeight += scores.getSecondYearFirstSemester().get(n).getCredits() +
                scores.getSecondYearSecondSemester().get(n).getCredits();
        researchWeight += scores.getThirdYearFirstSemester().get(n).getCredits() +
                scores.getThirdYearSecondSemester().get(n).getCredits();
        data.setResearchAvg(research / ScienceWeight);

        //국영수탐 백분율
        //국어 백분율
        double First;
        double Second;
        double KoreanAvg;
        double MathAvg;
        double SocialAvg;
        double ScienceAvg;
        double FirstGrade;
        double SecondGrade;
        double ThirdGrade;
        double EnglishAvg;
        double ResearchAvg;

        First = calculatePercentile(scores.getFirstYearFirstSemester().get(0).getRawScore(),scores.getFirstYearFirstSemester().get(0).getSubjectAverage(),scores.getFirstYearFirstSemester().get(0).getStandardD());
        Second = calculatePercentile(scores.getFirstYearFirstSemester().get(1).getRawScore(),scores.getFirstYearFirstSemester().get(1).getSubjectAverage(),scores.getFirstYearFirstSemester().get(1).getStandardD());
        double result = (First+Second)/2;


        First = calculatePercentile(scores.getFirstYearSecondSemester().get(0).getRawScore(),scores.getFirstYearSecondSemester().get(0).getSubjectAverage(),scores.getFirstYearSecondSemester().get(0).getStandardD());
        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(1).getRawScore(),scores.getFirstYearSecondSemester().get(1).getSubjectAverage(),scores.getFirstYearSecondSemester().get(1).getStandardD());
        result += (First+Second)/2;
        FirstGrade = result/2;


        First = calculatePercentile(scores.getSecondYearFirstSemester().get(0).getRawScore(),scores.getSecondYearFirstSemester().get(0).getSubjectAverage(),scores.getSecondYearFirstSemester().get(0).getStandardD());
        Second = calculatePercentile(scores.getSecondYearFirstSemester().get(1).getRawScore(),scores.getSecondYearFirstSemester().get(1).getSubjectAverage(),scores.getSecondYearFirstSemester().get(1).getStandardD());
        result =(First+Second)/2;


        First = calculatePercentile(scores.getSecondYearSecondSemester().get(0).getRawScore(),scores.getSecondYearSecondSemester().get(0).getSubjectAverage(),scores.getSecondYearSecondSemester().get(0).getStandardD());
        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(1).getRawScore(),scores.getSecondYearSecondSemester().get(1).getSubjectAverage(),scores.getSecondYearSecondSemester().get(1).getStandardD());
        result += (First+Second)/2;
        SecondGrade = result/2;


        First = calculatePercentile(scores.getThirdYearFirstSemester().get(0).getRawScore(),scores.getThirdYearFirstSemester().get(0).getSubjectAverage(),scores.getThirdYearFirstSemester().get(0).getStandardD());
        Second = calculatePercentile(scores.getThirdYearFirstSemester().get(1).getRawScore(),scores.getThirdYearFirstSemester().get(1).getSubjectAverage(),scores.getThirdYearFirstSemester().get(1).getStandardD());
        result = (First+Second)/2;



        First = calculatePercentile(scores.getThirdYearSecondSemester().get(0).getRawScore(),scores.getThirdYearSecondSemester().get(0).getSubjectAverage(),scores.getThirdYearSecondSemester().get(0).getStandardD());
        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(1).getRawScore(),scores.getThirdYearSecondSemester().get(1).getSubjectAverage(),scores.getThirdYearSecondSemester().get(1).getStandardD());
        result += (First+Second)/2;

        ThirdGrade = result/2;
        KoreanAvg = (FirstGrade+SecondGrade+ThirdGrade)/3;


        //수학

        First = calculatePercentile(scores.getFirstYearFirstSemester().get(2).getRawScore(),scores.getFirstYearFirstSemester().get(2).getSubjectAverage(),scores.getFirstYearFirstSemester().get(2).getStandardD());
        Second = calculatePercentile(scores.getFirstYearFirstSemester().get(3).getRawScore(),scores.getFirstYearFirstSemester().get(3).getSubjectAverage(),scores.getFirstYearFirstSemester().get(3).getStandardD());
        result = (First+Second)/2;


        First = calculatePercentile(scores.getFirstYearSecondSemester().get(2).getRawScore(),scores.getFirstYearSecondSemester().get(2).getSubjectAverage(),scores.getFirstYearSecondSemester().get(2).getStandardD());
        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(3).getRawScore(),scores.getFirstYearSecondSemester().get(3).getSubjectAverage(),scores.getFirstYearSecondSemester().get(3).getStandardD());
        result += (First+Second)/2;
        FirstGrade = result/2;


        First = calculatePercentile(scores.getSecondYearFirstSemester().get(2).getRawScore(),scores.getSecondYearFirstSemester().get(2).getSubjectAverage(),scores.getSecondYearFirstSemester().get(2).getStandardD());
        Second = calculatePercentile(scores.getSecondYearFirstSemester().get(3).getRawScore(),scores.getSecondYearFirstSemester().get(3).getSubjectAverage(),scores.getSecondYearFirstSemester().get(3).getStandardD());
        result = (First+Second)/2;


        First = calculatePercentile(scores.getSecondYearSecondSemester().get(2).getRawScore(),scores.getSecondYearSecondSemester().get(2).getSubjectAverage(),scores.getSecondYearSecondSemester().get(2).getStandardD());
        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(3).getRawScore(),scores.getSecondYearSecondSemester().get(3).getSubjectAverage(),scores.getSecondYearSecondSemester().get(3).getStandardD());
        result += (First+Second)/2;
        SecondGrade = result/2;


        First = calculatePercentile(scores.getThirdYearFirstSemester().get(2).getRawScore(),scores.getThirdYearFirstSemester().get(2).getSubjectAverage(),scores.getThirdYearFirstSemester().get(2).getStandardD());
        Second = calculatePercentile(scores.getThirdYearFirstSemester().get(3).getRawScore(),scores.getThirdYearFirstSemester().get(3).getSubjectAverage(),scores.getThirdYearFirstSemester().get(3).getStandardD());
        result = (First+Second)/2;


        First = calculatePercentile(scores.getThirdYearSecondSemester().get(2).getRawScore(),scores.getThirdYearSecondSemester().get(2).getSubjectAverage(),scores.getThirdYearSecondSemester().get(2).getStandardD());
        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(3).getRawScore(),scores.getThirdYearSecondSemester().get(3).getSubjectAverage(),scores.getThirdYearSecondSemester().get(3).getStandardD());
        result += (First+Second)/2;
        ThirdGrade = result/2;

        MathAvg = (FirstGrade+SecondGrade+ThirdGrade)/3;


        //사탐
        int sub = 5;
        int sub2 = 6;
        First = calculatePercentile(scores.getFirstYearFirstSemester().get(sub).getRawScore(),scores.getFirstYearFirstSemester().get(sub).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getFirstYearFirstSemester().get(sub2).getRawScore(),scores.getFirstYearFirstSemester().get(sub2).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub2).getStandardD());
        result = (First+Second)/2;


        First = calculatePercentile(scores.getFirstYearSecondSemester().get(sub).getRawScore(),scores.getFirstYearSecondSemester().get(sub).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(sub2).getRawScore(),scores.getFirstYearSecondSemester().get(sub2).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub2).getStandardD());
        result += (First+Second)/2;
        FirstGrade = result/2;


        First = calculatePercentile(scores.getSecondYearFirstSemester().get(sub).getRawScore(),scores.getSecondYearFirstSemester().get(sub).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getSecondYearFirstSemester().get(sub2).getRawScore(),scores.getSecondYearFirstSemester().get(sub2).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub2).getStandardD());
        result = (First+Second)/2;


        First = calculatePercentile(scores.getSecondYearSecondSemester().get(sub).getRawScore(),scores.getSecondYearSecondSemester().get(sub).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(sub2).getRawScore(),scores.getSecondYearSecondSemester().get(sub2).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub2).getStandardD());
        result += (First+Second)/2;
        SecondGrade = result/2;


        First = calculatePercentile(scores.getThirdYearFirstSemester().get(sub).getRawScore(),scores.getThirdYearFirstSemester().get(sub).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getThirdYearFirstSemester().get(sub2).getRawScore(),scores.getThirdYearFirstSemester().get(sub2).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub2).getStandardD());
        result = (First+Second)/2;


        First = calculatePercentile(scores.getThirdYearSecondSemester().get(sub).getRawScore(),scores.getThirdYearSecondSemester().get(sub).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(sub2).getRawScore(),scores.getThirdYearSecondSemester().get(sub2).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub2).getStandardD());
        result += (First+Second)/2;
        ThirdGrade = result/2;

        SocialAvg = (FirstGrade+SecondGrade+ThirdGrade)/3;
        //영어
        sub = 4;
        First = calculatePercentile(scores.getFirstYearFirstSemester().get(sub).getRawScore(),scores.getFirstYearFirstSemester().get(sub).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(sub).getRawScore(),scores.getFirstYearSecondSemester().get(sub).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub).getStandardD());
        result = (First+Second)/2;

        First = calculatePercentile(scores.getSecondYearFirstSemester().get(sub).getRawScore(),scores.getSecondYearFirstSemester().get(sub).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(sub).getRawScore(),scores.getSecondYearSecondSemester().get(sub).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub).getStandardD());
        result += (First+Second)/2;

        First = calculatePercentile(scores.getThirdYearFirstSemester().get(sub).getRawScore(),scores.getThirdYearFirstSemester().get(sub).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(sub).getRawScore(),scores.getThirdYearSecondSemester().get(sub).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub).getStandardD());
        result += (First+Second)/2;

        EnglishAvg = result/3;


        //과학
        sub = 7;
        First = calculatePercentile(scores.getFirstYearFirstSemester().get(sub).getRawScore(),scores.getFirstYearFirstSemester().get(sub).getSubjectAverage(),scores.getFirstYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getFirstYearSecondSemester().get(sub).getRawScore(),scores.getFirstYearSecondSemester().get(sub).getSubjectAverage(),scores.getFirstYearSecondSemester().get(sub).getStandardD());
        result = (First+Second)/2;


        First = calculatePercentile(scores.getSecondYearFirstSemester().get(sub).getRawScore(),scores.getSecondYearFirstSemester().get(sub).getSubjectAverage(),scores.getSecondYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getSecondYearSecondSemester().get(sub).getRawScore(),scores.getSecondYearSecondSemester().get(sub).getSubjectAverage(),scores.getSecondYearSecondSemester().get(sub).getStandardD());
        result += (First+Second)/2;


        First = calculatePercentile(scores.getThirdYearFirstSemester().get(sub).getRawScore(),scores.getThirdYearFirstSemester().get(sub).getSubjectAverage(),scores.getThirdYearFirstSemester().get(sub).getStandardD());
        Second = calculatePercentile(scores.getThirdYearSecondSemester().get(sub).getRawScore(),scores.getThirdYearSecondSemester().get(sub).getSubjectAverage(),scores.getThirdYearSecondSemester().get(sub).getStandardD());
        result += (First+Second)/2;

        ScienceAvg = result/3;

        ResearchAvg = (ScienceAvg+SocialAvg)/2;

        data.setPrecen((KoreanAvg+EnglishAvg+MathAvg+ResearchAvg)-220);

        return data;
    }

    public static double calculatePercentile(double rawScore, double mean, double standardDeviation) {
        // 표준 정규 분포 생성
        NormalDistribution normalDistribution = new NormalDistribution(mean, standardDeviation);

        // Z-점수 계산
        double zScore = (rawScore - mean) / standardDeviation;

        // Z-점수의 부호에 따라 계산 수행
        double percentile;
        if (zScore >= 0) {
            // 양수인 경우: 평균보다 높은 위치
            percentile = (1 - normalDistribution.cumulativeProbability(zScore)) * 100;
        } else {
            // 음수인 경우: 평균보다 낮은 위치
            percentile = (1-normalDistribution.cumulativeProbability(-zScore))* 100;
        }

        return percentile;
    }
}
