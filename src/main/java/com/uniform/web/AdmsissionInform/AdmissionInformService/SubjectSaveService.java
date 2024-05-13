package com.uniform.web.AdmsissionInform.AdmissionInformService;

import com.uniform.web.AdmsissionInform.Repository.SchoolYearRepository;
import com.uniform.web.AdmsissionInform.Repository.ScoreRepository;
import com.uniform.web.AdmsissionInform.Repository.SubjectRepository;
import com.uniform.web.AdmsissionInform.entity.SchoolYearEntity;
import com.uniform.web.AdmsissionInform.entity.ScoreEntity;
import com.uniform.web.AdmsissionInform.entity.SubjectsEntity;
import com.uniform.web.member.entity.MemberEntity;
import com.uniform.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectSaveService {
    private final SchoolYearRepository schoolYearRepository;
    private final SubjectRepository subjectRepository;
    private final MemberRepository memberRepository;
    private final ScoreRepository scoreRepository;
    public SchoolYearEntity findSchoolEntity(int Year, int Term){
        SchoolYearEntity schoolYearEntity = schoolYearRepository.findAllBySchoolYearAndSchoolTerm(Year,Term);
        if (schoolYearEntity == null){
            return null;
        }
        else{
            return schoolYearEntity;
        }
    }
    public SubjectsEntity findSubjectEntity(String SubjectName, String Curriculum){
        if (SubjectName == null || Curriculum == null){
            return null;
        }
        SubjectsEntity subjectsEntity = subjectRepository.findAllBySubjectAndCurriculum(SubjectName,Curriculum);
        if (subjectsEntity == null){
            return null;
        }
        return subjectsEntity;
    }
    public MemberEntity findMemberEntity(String memberId){
        if (memberId == null){
            return null;
        }
        MemberEntity memberEntity = memberRepository.findAllByMemberId(memberId);
        if (memberEntity == null){
            return null;
        }
        return memberEntity;
    }
    public boolean saveScore(ScoreEntity scoreEntity){
        try {
            scoreRepository.save(scoreEntity);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
