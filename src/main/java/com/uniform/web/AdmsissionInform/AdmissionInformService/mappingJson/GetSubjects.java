package com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson;

import com.uniform.web.AdmsissionInform.Repository.SubjectRepository;
import com.uniform.web.AdmsissionInform.entity.SubjectsEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@RequiredArgsConstructor
public class GetSubjects {
    //국어
    private List<String> 국어;
    //수학
    private List<String> 수학;
    //영어
    private List<String> 영어;
    //사회
    private List<String> 사회;
    //과학
    private List<String> 과학;
    //체육
    private List<String> 체육;
    //예술
    private List<String> 예술;
    //기술 가정
    private List<String> 기술가정;
    public List<String> getSubjectList(List<SubjectsEntity> subjectsEntities,String curriculum){
        return (subjectsEntities.stream()
                .filter(x->x.getCurriculum().equals(curriculum))
                .map(SubjectsEntity::getSubject)
                .collect(Collectors.toList()));
    }
}
